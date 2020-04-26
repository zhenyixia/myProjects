package com.lyp.springboot01.authmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FreemarkerTestController {

    @RequestMapping("/helloFreemarker")
    public String helloWorld(Model model) {
        model.addAttribute("content", "Freemarker");
        return "index";
    }


}
