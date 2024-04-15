package com.apple.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class BasicController {
    @GetMapping("/")
    @ResponseBody
    String hello() {
        return "hello world";
    }

    @GetMapping("/about")
    @ResponseBody
    String about() {
        return "This is phishing site";
    }

    @GetMapping("/welcome")
    String welcome() {
        return "index.html";
    }

    @GetMapping("/date")
    @ResponseBody
    String date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}