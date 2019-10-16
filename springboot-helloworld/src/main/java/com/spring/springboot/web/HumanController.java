package com.spring.springboot.web;

import com.spring.springboot.service.Human;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frankie Yang on 2019-10-16.
 */
@Controller
public class HumanController {

    @Value("${human.quantity}")
    private int quantity;

    @GetMapping("/human")
    public String index(Model model) {
        List<Human> hs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Human h = new Human();
            h.setId((long) i);
            h.setUsername("javaboy>>>>" + i);
            h.setAddress("www.javaboy.org>>>>" + i);
            hs.add(h);
        }
        model.addAttribute("humans", hs);
        return "index";
    }

    @GetMapping("/human2")
    public String index2(Model model) {
        List<Human> hs = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Human h = new Human();
            h.setId((long) i);
            h.setUsername("javaboy>>>>" + i);
            h.setAddress("www.javaboy.org>>>>" + i);
            hs.add(h);
        }
        model.addAttribute("humans", hs);
        return "index";
    }

}
