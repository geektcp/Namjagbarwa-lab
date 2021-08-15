/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.geektcp.alpha.socket.netty.handler;

import com.geektcp.alpha.socket.netty.repository.ChannelRepository;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Before;
import org.junit.Test;

import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Test for  LoginHandler.java
 *
 * @author Jibeom Jung akka. Manty
 */
public class LoginHandlerTest {

    private ChannelRepository channelRepository;

    @Before
    void setup() {
        channelRepository = mock(ChannelRepository.class);
    }

    @Test
    void testLogin() {
        // given
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new LoginHandler(this.channelRepository));

        // when
        embeddedChannel.writeInbound("login aaa\r\n");

        // then
        Queue<Object> outboundMessages = embeddedChannel.outboundMessages();
        assertThat(outboundMessages.poll()).isEqualTo("Successfully logged in as aaa. \r\n");
    }
}