package com.walmartlabs.sca.kafka.router.route;

import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaConsumer extends RouteBuilder {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@Override
	public void configure() throws Exception {
		PropertiesComponent pc = getContext().getComponent("properties", PropertiesComponent.class);
        pc.setLocation("classpath:application.properties");

        log("About to start route: Kafka Server -> Log ");

        from("kafka:{{consumer.topic}}?brokers={{kafka.host}}:{{kafka.port}}"
                + "&maxPollRecords={{consumer.maxPollRecords}}"
                + "&consumersCount={{consumer.consumersCount}}"
                + "&seekTo={{consumer.seekTo}}"
                + "&groupId={{consumer.group}}")
                .routeId("FromKafka")
       .log("Message body: ${body}");

	}
	
	private void log(Object b) {
        LOGGER.info("body is: {}", b);
    }

    private void log(Message m) {
        LOGGER.info("message is: {}", m);
    }

}
