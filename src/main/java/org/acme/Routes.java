package org.acme;

import org.apache.camel.builder.RouteBuilder;

public class Routes extends RouteBuilder {

    @Override
    public void configure() throws Exception {

    
        // Invokes a simple greeting endpoint every 10 seconds
        from("timer:hello?period=5000")
        .log("Hello")
        ;

    }
}