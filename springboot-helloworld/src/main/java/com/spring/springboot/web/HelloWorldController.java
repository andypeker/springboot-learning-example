package com.spring.springboot.web;

import com.spring.springboot.service.Hello;
import com.spring.springboot.service.UserSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.System.out;

/**
 * Spring Boot HelloWorld 案例
 *
 * Created by bysocket on 16/4/26.
 */
@RestController(value = "/www")
public class HelloWorldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    /*@Value("${user.name}")
    private String name1;

    @Value("${user.age}")
    private int age;*/
    @Autowired
    private UserSetting user;

    @Autowired
    private Hello hello;

    @RequestMapping("/")
    public String sayHello() {
        LOGGER.warn("default page ...");
        return "Hello, World !\n" + user.getUserName() + "\t" + user.getAge();
    }

    @RequestMapping("/hello")
    public String helloHandler(){
        return hello.sayHello();
    }

    @RequestMapping("/whyang")
    @ResponseBody
    public String sayFrankie(){
        out.println("\n\nHelloWorldController\n\n");
        return "Frankie, Spring Boot run ..";
    }
}
