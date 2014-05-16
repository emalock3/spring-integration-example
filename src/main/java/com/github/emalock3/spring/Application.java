package com.github.emalock3.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.context.IntegrationContextUtils;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

@ComponentScan
@IntegrationComponentScan
@EnableAutoConfiguration
public class Application {
    public static void main(String ... args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .showBanner(false)
                .run(args);
        MessageChannel requestChannel = context.getBean("greetingInboundChannel", MessageChannel.class);
        requestChannel.send(MessageBuilder.withPayload("World").build());
        // shutdown taskScheduler
        try {
            context.getBean(IntegrationContextUtils.TASK_SCHEDULER_BEAN_NAME, DisposableBean.class).destroy();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        context.stop();
    }
}
