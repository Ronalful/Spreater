package com.dmitry.spreater.controller;

import com.dmitry.spreater.domain.Message;
import com.dmitry.spreater.domain.User;
import com.dmitry.spreater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageRepo.findAll();

        if (filter != null && !filter.isEmpty())
            messages = messageRepo.findByTag(filter);
        else
            messages = messageRepo.findAll();

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Model model,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        if (!text.isEmpty() && !tag.isEmpty()) {
            Message message = new Message();
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                message.setFilename(resultFilename);
            }
            message.setText(text);
            message.setTag(tag);
            message.setAuthor(user);
            messageRepo.save(message);
        }

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        model.addAttribute("filter", "");
        return "main";
    }

}