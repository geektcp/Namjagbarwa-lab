package com.geektcp.alpha.agent.builder;

import com.geektcp.alpha.agent.advice.MappingAdvisor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

/**
 * @author haiyang.tang on 11.27 027 11:21:34.
 */
public class BuddyAgentBuilderTest {

    public static void main(String[] args) {
        Instrumentation instrumentation = null;
        AgentBuilder.InitializationStrategy.SelfInjection.Eager eager = new AgentBuilder.InitializationStrategy.SelfInjection.Eager();
        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) ->
                builder.method(ElementMatchers.any())
                        .intercept(Advice.to(MappingAdvisor.class));

        AgentBuilder agentBuilder = new AgentBuilder.Default();
        System.out.println("agentBuilder:" + agentBuilder);

        AgentBuilder agentBuilder22 = agentBuilder.with(eager);
        System.out.println("agentBuilder22:" + agentBuilder22);

        agentBuilder.with(eager)
                .type(ElementMatchers.nameStartsWith("com.casstime"))
                .transform(transformer);
        agentBuilder.installOn(instrumentation);

        AgentBuilder agentBuilder2 = new AgentBuilder.Default()
                .with(eager)
                .type(ElementMatchers.nameStartsWith("com.casstime"))
                .transform(transformer);

        agentBuilder2.installOn(instrumentation);
        System.out.println("==============");
    }
}
