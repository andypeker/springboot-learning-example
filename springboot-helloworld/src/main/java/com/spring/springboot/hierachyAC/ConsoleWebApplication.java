package com.spring.springboot.hierachyAC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Frankie Yang on 2019-01-29.
 */
@SpringBootApplication(exclude = DispatcherServletAutoConfiguration.class)
@ComponentScan(
        basePackages = "com",
        excludeFilters =
        @ComponentScan.Filter({Controller.class, ControllerAdvice.class}))
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ConsoleWebApplication  {

    public static void main(String[] args) {
        SpringApplication.run(ConsoleWebApplication.class, args);
    }
}
