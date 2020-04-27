package com.lyp.springboot01.authmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class FreemarkerTestController {

    @RequestMapping(value = "freemarker")
    public String helloWorld(Model model) {
        model.addAttribute("content", "Freemarker");
        return "index";
    }


}
