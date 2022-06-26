package com.david.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String login() {
        return "blog/login";
    }

    @RequestMapping("/inicio")
    public String index() {
        return "blog/inicio";
    }

    @RequestMapping("/jogos")
    public String games() {
        return "blog/jogos";
    }

    @RequestMapping("/sobre")
    public String about() {
        return "blog/sobre";
    }

    @RequestMapping("/contato")
    public String contact() {
        return "blog/contato";
    }
}
