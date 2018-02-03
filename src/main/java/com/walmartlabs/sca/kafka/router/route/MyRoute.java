package com.walmartlabs.sca.kafka.router.route;

import java.util.Objects;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRoute extends RouteBuilder {

	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyRoute.class);
	
	@Override
	public void configure() throws Exception {
		from("timer:simple?period=503")
        .id("simple-route")
        .transform()
            .exchange(this::dateToTime)
        .process()
            .message(this::log)
        .process()
            .body(this::log)
        .choice()
            .when()
                .body(Integer.class, b -> (b & 1) == 0)
                .log("Received even number")
            .when()
                .body(Integer.class, (b, h) -> h.containsKey("skip") ? false : (b & 1) == 0)
                .log("Received odd number")
            .when()
                .body(Objects::isNull)
                .log("Received null body")
            .when()
                .body(Integer.class, b -> (b & 1) != 0)
                .log("Received odd number")
        .endChoice();

	}
	
	private Long dateToTime(Exchange e) {
        return e.getProperty(Exchange.TIMER_FIRED_TIME, Date.class).getTime();
    }

    private void log(Object b) {
        LOGGER.info("body is: {}", b);
    }

    private void log(Message m) {
        LOGGER.info("message is: {}", m);
    }

}
