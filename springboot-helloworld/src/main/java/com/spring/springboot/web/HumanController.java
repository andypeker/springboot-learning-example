package com.spring.springboot.web;

import com.spring.springboot.service3.Human;
import com.spring.springboot.service3.Info;
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

    @GetMapping("/humaninfo")
    public String indexinfo(Model model) {
        List<Human> hs = new ArrayList<>();
        List<Info> infos = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Human h = new Human();
            h.setId((long) i);
            h.setUsername("javaboy>>>>" + i);
            h.setAddress("www.javaboy.org>>>>" + i);
            hs.add(h);
        }

        for (int i = 0; i < quantity; i++) {
            Info info = new Info();
            info.setAddr("china addr " + i);
            info.setIncome(10000 + 1000 * i);
            info.setGender(i%2 == 0 ? "male" : "female");
            info.setNationality(i%2 == 0 ? "CHN" : "Aus");
            infos.add(info);
        }
        model.addAttribute("humans", hs);
        model.addAttribute("infos", infos);

        return "index2";
    }

}
