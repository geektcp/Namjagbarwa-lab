package com.geektcp.alpha.agent.transformer;//package com.geektcp.alpha.agent.transformer;
//
//import net.bytebuddy.agent.builder.AgentBuilder;
//import net.bytebuddy.asm.Advice;
//import net.bytebuddy.description.type.TypeDescription;
//import net.bytebuddy.dynamic.DynamicType;
//import net.bytebuddy.matcher.ElementMatchers;
//import net.bytebuddy.utility.JavaModule;
//
///**
// * @author haiyang.tang on 12.06 006 14:38:42.
// */
//public class GetMappingTransformer implements AgentBuilder.Transformer {
//    @Override
//    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
//        return builder
//                .method(ElementMatchers.declaresAnnotation(ElementMatchers.annotationType(PostMapping.class)))
//                .intercept(Advice.to(PostMappingAdvisor.class))
//                .method(ElementMatchers.declaresAnnotation(ElementMatchers.annotationType(GetMapping.class)))
//                .intercept(Advice.to(GetMappingAdvisor.class))
//                .method(ElementMatchers.declaresAnnotation(ElementMatchers.annotationType(RequestMapping.class)))
//                .intercept(Advice.to(RequestMappingAdvisor.class));
//    }
//}
