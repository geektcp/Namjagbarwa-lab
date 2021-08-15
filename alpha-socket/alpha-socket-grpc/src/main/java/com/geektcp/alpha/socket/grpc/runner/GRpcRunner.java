package com.geektcp.alpha.socket.grpc.runner;

import com.geektcp.alpha.socket.grpc.annotation.RpcGlobalInterceptor;
import com.geektcp.alpha.socket.grpc.annotation.RpcBuilderConfigurer;
import com.geektcp.alpha.socket.grpc.annotation.RpcService;
import io.grpc.*;
import io.grpc.health.v1.HealthCheckResponse;
import io.grpc.protobuf.services.ProtoReflectionService;
import io.grpc.services.HealthStatusManager;
import com.geektcp.alpha.socket.grpc.autoconfig.RpcProperties;
import com.geektcp.alpha.socket.grpc.context.GRpcInitializedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
@Slf4j
public class GRpcRunner implements CommandLineRunner, DisposableBean {

    @Autowired
    private HealthStatusManager healthStatusManager;

    @Autowired
    private AbstractApplicationContext applicationContext;

    @Autowired
    private RpcProperties serverProperties;

    private final ServerBuilder<?> serverBuilder;

    private RpcBuilderConfigurer serverBuilderConfigurer;

    private Server server;

    public GRpcRunner(ServerBuilder<?> serverBuilder, RpcBuilderConfigurer serverBuilderConfigurer) {
        this.serverBuilder = serverBuilder;
        this.serverBuilderConfigurer = serverBuilderConfigurer;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting gRPC Server {}:{}",serverProperties.getHost(),serverProperties.getPort());

        Collection<ServerInterceptor> globalInterceptors =
                getBeanNamesByTypeWithAnnotation(RpcGlobalInterceptor.class, ServerInterceptor.class)
                .map(name -> applicationContext.getBeanFactory().getBean(name, ServerInterceptor.class))
                .collect(Collectors.toList());


        // Adding health service
        serverBuilder.addService(healthStatusManager.getHealthService());

        // find and register all GrpcService-enabled beans
        getBeanNamesByTypeWithAnnotation(RpcService.class, BindableService.class)
                .forEach(name -> {
                    BindableService srv = applicationContext.getBeanFactory().getBean(name, BindableService.class);
                    ServerServiceDefinition serviceDefinition = srv.bindService();
                    RpcService rpcServiceAnn = applicationContext.findAnnotationOnBean(name, RpcService.class);
                    serviceDefinition = bindInterceptors(serviceDefinition, rpcServiceAnn, globalInterceptors);
                    serverBuilder.addService(serviceDefinition);
                    String serviceName = serviceDefinition.getServiceDescriptor().getName();
                    healthStatusManager.setStatus(serviceName, HealthCheckResponse.ServingStatus.SERVING);

                    log.info("'{}' service has been registered", srv.getClass().getName());

                });

        if (serverProperties.isEnableReflection()) {
            serverBuilder.addService(ProtoReflectionService.newInstance());
            log.info("'{}' service has been registered", ProtoReflectionService.class.getName());
        }

        serverBuilderConfigurer.configure(serverBuilder);
        server = serverBuilder.build().start();
        applicationContext.publishEvent(new GRpcInitializedEvent(server));

        log.info("gRPC Server started");
        startDaemonAwaitThread();
    }

    private ServerServiceDefinition bindInterceptors(ServerServiceDefinition serviceDefinition, RpcService rpcService, Collection<ServerInterceptor> globalInterceptors) {
        Stream<? extends ServerInterceptor> privateInterceptors = Stream.of(rpcService.interceptors())
                .map(interceptorClass -> {
                    try {
                        return 0 < applicationContext.getBeanNamesForType(interceptorClass).length ?
                                applicationContext.getBean(interceptorClass) :
                                interceptorClass.newInstance();
                    } catch (Exception e) {
                        throw new BeanCreationException("Failed to create interceptor instance.", e);
                    }
                });

        List<ServerInterceptor> interceptors = Stream.concat(
                rpcService.applyGlobalInterceptors() ? globalInterceptors.stream() : Stream.empty(),
                privateInterceptors)
                .distinct()
                .sorted(serverInterceptorOrderComparator())
                .collect(Collectors.toList());
        return ServerInterceptors.intercept(serviceDefinition, interceptors);
    }

    private Comparator<Object> serverInterceptorOrderComparator() {
        Function<Object, Boolean> isOrderAnnotated = obj -> {
            Order ann = obj instanceof Method ? AnnotationUtils.findAnnotation((Method) obj, Order.class) :
                    AnnotationUtils.findAnnotation(obj.getClass(), Order.class);
            return ann != null;
        };
        return AnnotationAwareOrderComparator.INSTANCE.thenComparing((o1, o2) -> {
            boolean p1 = isOrderAnnotated.apply(o1);
            boolean p2 = isOrderAnnotated.apply(o2);
            return p1 && !p2 ? -1 : p2 && !p1 ? 1 : 0;
        }).reversed();
    }


    private void startDaemonAwaitThread() {
        Thread awaitThread = new Thread(() -> {
            try {
                GRpcRunner.this.server.awaitTermination();
            } catch (InterruptedException e) {
                log.error("gRPC server stopped.", e);
            }
        });
        awaitThread.setDaemon(false);
        awaitThread.start();
    }

    @Override
    public void destroy() throws Exception {
        log.info("Shutting down gRPC server ...");
        server.getServices().forEach(def -> healthStatusManager.clearStatus(def.getServiceDescriptor().getName()));
        Optional.ofNullable(server).ifPresent(Server::shutdown);
        log.info("gRPC server stopped.");
    }

    private <T> Stream<String> getBeanNamesByTypeWithAnnotation(Class<? extends Annotation> annotationType, Class<T> beanType) throws Exception {

        return Stream.of(applicationContext.getBeanNamesForType(beanType))
                .filter(name -> {
                    final BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition(name);
                    final Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(annotationType);

                    if (beansWithAnnotation.containsKey(name)) {
                        return true;
                    } else if (beanDefinition.getSource() instanceof AnnotatedTypeMetadata) {
                        return AnnotatedTypeMetadata.class.cast(beanDefinition.getSource()).isAnnotated(annotationType.getName());

                    }

                    return false;
                });
    }

}
