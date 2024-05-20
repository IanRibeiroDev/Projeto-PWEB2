package br.edu.ifpb.pweb2.armants.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Bem-vindo à página inicial Julyana =D!");
        return "home";
    }
}