package com.spring.springboot.hierachyAC;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Frankie Yang on 2019-01-29.
 */
@EnableWebMvc
@ComponentScan(
        basePackages = "com",
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter(WebController.class)
)
public class WebConfig extends WebMvcConfigurerAdapter {

    @PostConstruct
    public void init(){
        System.out.println("-----------------------WebConfig");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CurrentUserMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

//    ......
//    ......
}

class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public boolean supportsParameter(MethodParameter parameter){
        return true;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                           NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return new Object();
    }

}

class WebController {

}