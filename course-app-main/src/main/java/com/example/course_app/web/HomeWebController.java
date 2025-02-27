package com.example.course_app.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")  // Maps to "/"
public class HomeWebController {

    @GetMapping
    public String home(Model model) {
        // Load any initial data if needed
        return "home"; // Render 'home.html' using 'base.html'
    }

}
