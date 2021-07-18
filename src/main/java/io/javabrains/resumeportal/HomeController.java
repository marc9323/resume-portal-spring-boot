package io.javabrains.resumeportal;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "hello";
    }

    @GetMapping("/edit")
    public String edit() {
        return "edit page";
    }

    // experimental
    @GetMapping("/admin")
    public String admin() {
        return "<h2>ADMINS WELCOME HERE</h2>";
    }

    @GetMapping("/user")
    public String user() {
       return "USERS WELCOME HERE";
    }
}
