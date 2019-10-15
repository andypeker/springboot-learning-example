package com.spring.springboot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Frankie Yang on 2019-10-15.
 */
@RestController
@RequestMapping("/tc")
public class TestRestController{

    // 返回 return 里面的内容
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(){
        // 返回 return 里面的内容 如字符串 json xml 或自定义返回
        return "{tc}";
    }
}
