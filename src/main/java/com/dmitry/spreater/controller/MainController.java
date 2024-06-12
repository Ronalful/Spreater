package com.dmitry.spreater.controller;

import com.dmitry.spreater.domain.Message;
import com.dmitry.spreater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Model model) {
        if (!text.isEmpty() && !tag.isEmpty()) {
            messageRepo.save(new Message(text, tag));
        }

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/main/filter")
    public String filter(@RequestParam String filter, Model model) {
        Iterable<Message> messages;
        if (!filter.isEmpty())
            messages = messageRepo.findByTag(filter);
        else
            messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }
}