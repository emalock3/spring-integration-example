package com.github.emalock3.spring;

import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.support.PeriodicTrigger;

@Configuration
@EnableIntegration
public class IntegrationConfiguration {
    
    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata defaultPoller() {
        PollerMetadata pm = new PollerMetadata();
        pm.setTrigger(new PeriodicTrigger(1, TimeUnit.SECONDS));
        return pm;
    }
    
    @Bean
    public MessageChannel greetingInboundChannel() {
        return MessageChannels.direct(GreetingService.GREETING_INBOUND_CHANNEL_NAME).get();
    }
//    
//    @Bean
//    public IntegrationFlow helloFlow() {
//        return IntegrationFlows.from(greetingInboundChannel())
//                .transform(source -> "Hello, " + source + "!")
//                .handle(message -> System.out.println(message.getPayload()))
//                .get();
//    }
}
