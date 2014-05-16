package com.github.emalock3.spring;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class GreetingService {

    public static final String GREETING_INBOUND_CHANNEL_NAME = "greetingInboundChannel";
    
    @ServiceActivator(inputChannel = GREETING_INBOUND_CHANNEL_NAME)
    public void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }
    
}
