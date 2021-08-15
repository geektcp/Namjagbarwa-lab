package com.geektcp.alpha.agent.transformer;

import com.geektcp.alpha.agent.advice.MappingAdvisor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

/**
 * @author haiyang.tang on 12.06 006 14:38:42.
 */
public class MappingTransformer implements AgentBuilder.Transformer {

    @Override
    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder,
                                            TypeDescription typeDescription,
                                            ClassLoader classLoader,
                                            JavaModule module) {
        return builder
                .method(ElementMatchers.isAnnotatedWith(ElementMatchers.isAnnotation()))
                .intercept(Advice.to(MappingAdvisor.class));
    }
}
