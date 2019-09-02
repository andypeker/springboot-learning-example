package com.spring.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Frankie Yang on 2019-09-02.
 */
@Controller
public class DefaultPage {

    @RequestMapping(value = "/")
    @ResponseBody
    public String defaultPage(){
        System.out.println("\ndefaultPage defaultPage\n");
        return "(springboot-freemarker) Default Page";
    }
}
