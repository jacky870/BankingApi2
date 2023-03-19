package banking.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component()
public class MyRouteBuilder extends RouteBuilder {
// This is added from feature branch
    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("jetty")
                .contextPath("/")
                .port(8080)
                .enableCORS(true)
                .corsAllowCredentials(true)
                .corsHeaderProperty("Access-Control-Allow-Origin","*")
                .corsHeaderProperty("Access-Control-Allow-Headers","Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization")
        ;


        rest("/users").enableCORS(true)
                .get("/{id}").to("direct:getUser");

        from("direct:getUser")
                .routeId("getUserRoute")
                .log("hello world")
                .setBody(simple("hello world"))
                .setHeader("CamelHttpResponseCode", constant(200));
    }
}
