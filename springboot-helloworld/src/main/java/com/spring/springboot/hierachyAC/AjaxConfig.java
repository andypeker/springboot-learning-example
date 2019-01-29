package com.spring.springboot.hierachyAC;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.ControllerAdviceBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.Controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @author Frankie Yang on 2019-01-29.
 */
@EnableWebMvc
@ComponentScan(
        basePackages = "com",
        useDefaultFilters = false,
        includeFilters =
        @ComponentScan.Filter({AjaxController.class, AjaxControllerAdvice.class}))
public class AjaxConfig extends WebMvcConfigurerAdapter {

    @PostConstruct
    public void init(){
        System.out.println("-----------------------AjaxConfig");
    }


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Marshaller marshaller;

    @Autowired
    Unmarshaller unmarshaller;

//    ......
//    ......
}


class AjaxController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
        return null;
    }

}

@ControllerAdvice
class AjaxControllerAdvice {

}