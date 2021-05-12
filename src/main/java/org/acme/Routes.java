package org.acme;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;

public class Routes extends RouteBuilder {

    String mockresult = "{ \"profileid\": 123456, \"patient\": \"Me\", \"complaint\": \"Cold Symptoms\", \"diagnosis\": \"Acute Bronchitis\", \"prescription\": [{ \"medicine\": \"benzonatate\",  \"dosage\": \"3 times per day\" }, { \"medicine\": \"ProAir HFA\", \"dosage\": \"every 4 to 6 hours\" }], \"pharmancy\": \"CVS\" }";
    String statusError404 = "{ \"status\": \"NOT FOUND\"}";
    String statusError201 = "{ \"status\": \"CREATED!\"}";

    @Override
    public void configure() throws Exception {
        rest()
            .get("/prescription")
                .to("direct:getProfile")
 
            .put("/prescription")
                .consumes("application/json")
                .to("direct:putProfile")
           ;

        from("direct:getProfile")
                .log("START GET")
                .setBody().simple(mockresult)
                .choice()
                  .when(simple("${header.profileid} != 123456"))
                    .setBody().simple(statusError404)
                    .setHeader(Exchange.HTTP_RESPONSE_CODE,constant(404))
                .end()
                .to("log:info")
                ;
          
        from("direct:putProfile")
                  .log("START PUT")
                  .log("headers--> ${headers}")
                  .to("log:info")  
                  .setBody().simple(statusError201)
                  .setHeader(Exchange.HTTP_RESPONSE_CODE,constant(201))
                ;
        

    }
}