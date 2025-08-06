package com.dilaraalk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    // Bu endpoint'e sadece ROLE_ADMIN sahip kullanıcı erişebilir
    @GetMapping("/admin/test")
    public String adminTest() {
        return "✅ Admin sayfasına erişildi!";
    }

    // Bu endpoint'e ROLE_USER VEYA ROLE_ADMIN erişebilir
    @GetMapping("/user/test")
    public String userTest() {
        return "✅ User sayfasına erişildi!";
    }
}
