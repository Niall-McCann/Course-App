package com.example.course_app.web;

import com.example.course_app.entity.Lecturer;
import com.example.course_app.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lecturers")
public class LecturerWebController {

    @Autowired
    private LecturerService lecturerService;

    // Display the list of lecturers and the form to add a new lecturer
    @GetMapping
    public String listLecturers(Model model) {
        List<Lecturer> lecturers = lecturerService.getAllLecturers();
        model.addAttribute("lecturers", lecturers);
        model.addAttribute("lecturer", new Lecturer()); // Used for form binding
        return "lecturers";  // Corresponds to the Thymeleaf template for managing lecturers
    }

    // Handle the creation of a new lecturer
    @PostMapping
    public String createLecturer(@ModelAttribute Lecturer lecturer) {
        lecturerService.createLecturer(lecturer);
        return "redirect:/lecturers";
    }

    // Display the form to edit an existing lecturer
    @GetMapping("/edit/{id}")
    public String editLecturer(@PathVariable Long id, Model model) {
        Lecturer lecturer = lecturerService.getLecturerById(id).orElseThrow(() -> new IllegalArgumentException("Invalid lecturer Id:" + id));
        model.addAttribute("lecturer", lecturer);
        return "edit-lecturer";  // Template for editing lecturers
    }

    // Handle the update of an existing lecturer
    @PostMapping("/update/{id}")
    public String updateLecturer(@PathVariable Long id, @ModelAttribute Lecturer lecturer) {
        lecturerService.updateLecturer(id, lecturer);
        return "redirect:/lecturers";
    }

    // Handle the deletion of a lecturer
    @PostMapping("/delete/{id}")
    public String deleteLecturer(@PathVariable Long id) {
        lecturerService.deleteLecturer(id);
        return "redirect:/lecturers";
    }
}