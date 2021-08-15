package com.geektcp.alpha.agent.builder;

import com.geektcp.alpha.agent.transformer.AnyTransformer;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

import java.lang.instrument.Instrumentation;

import static com.geektcp.alpha.agent.util.LogUtil.log;
import static net.bytebuddy.matcher.ElementMatchers.*;


/**
 * @author haiyang.tang on 11.27 027 9:57:59.
 */
public class BuddyBuilder {

    private BuddyBuilder() {
    }

    public static void build(Instrumentation instrumentation) {
        try {
            ElementMatcher.Junction<TypeDescription> ignore = nameStartsWith("com.sun.")
                    .or(nameStartsWith("com.sun."))
                    .or(nameStartsWith("sun."))
                    .or(nameStartsWith("jdk."))
                    .or(nameStartsWith("org.aspectj."))
                    .or(nameStartsWith("org.groovy."))
                    .or(nameStartsWith("com.p6spy."))
                    .or(nameStartsWith("com.netflix."))
                    .or(nameStartsWith("io.netty."))
                    .or(nameStartsWith("javax."))
                    .or(nameStartsWith("io.swagger."))
                    .or(nameStartsWith("springfox.documentation."))
                    .or(nameStartsWith("net.bytebuddy."))
                    .or(nameStartsWith("com.geektcp.job.core.rpc."))
                    .or(nameStartsWith("com.geektcp.ec.cloud.uniqueId."))
                    .or(nameStartsWith("com.geektcp.ec.cloud.quote.message."))
                    .or(nameContains("slf4j"))
                    .or(nameContains("log4j"))
                    .or(nameContains("javassist"))
                    .or(nameContains(".asm."))
                    ;

            ElementMatcher.Junction<TypeDescription> filter = nameStartsWith("org.springframework.web.servlet.FrameworkServlet");
            AgentBuilder.InitializationStrategy.SelfInjection.Eager eager = new AgentBuilder.InitializationStrategy.SelfInjection.Eager();

//            AgentBuilder.Transformer transformer = new MappingTransformer();
            AgentBuilder.Transformer transformer = new AnyTransformer();
//            AgentBuilder.Transformer transformer = new TimeTransformer();
//            AgentBuilder.Transformer transformer = new ExceptionTransformer();

            AgentBuilder agentBuilder = new AgentBuilder.Default()
                    .with(eager)
                    .ignore(ignore)
                    .type(filter)
                    .transform(transformer);
            agentBuilder.installOn(instrumentation);
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

}
