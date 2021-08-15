package com.geektcp.alpha.agent;

/**
 * @author haiyang.tang on 11.14 014 16:29:31.
 */
import com.geektcp.alpha.agent.builder.BuddyBuilder;
import com.geektcp.alpha.agent.builder.HttpBuilder;

import java.lang.instrument.Instrumentation;

public class Agent {

    private Agent() {
    }

    public static void premain(String arguments, Instrumentation instrumentation) {
        BuddyBuilder.build(instrumentation);
        new Thread(){
            @Override
            public void run() {
                HttpBuilder.build();
            }
        }.start();
    }
}