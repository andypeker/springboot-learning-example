package com.spring.springboot.hierachyAC;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author Frankie Yang on 2019-01-29.
 */
@Order(Ordered.LOWEST_PRECEDENCE)
@Configuration
public class RootConfig extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ConsoleWebApplication.class)
                .child(WebConfig.class)
                .web(true)
                .sibling(AjaxConfig.class)
                .web(true);
    }

//    ......
//    ......
    public someotherfunc(){
        new SpringApplicationBuilder()
          .parent(ParentConfig.class).web(WebApplicationType.NONE)
          .child(WebConfig.class).web(WebApplicationType.SERVLET)
          .sibling(RestConfig.class).web(WebApplicationType.SERVLET)
          .run(args);
    }

}
