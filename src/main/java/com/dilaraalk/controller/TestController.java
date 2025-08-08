package com.dilaraalk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/admin/test")
    public String adminTest() {
        return "Admin sayfasına erişildi!";
    }

    @GetMapping("/user/test")
    public String userTest() {
        return "User sayfasına erişildi!";
    }
}
