package com.example.course_app.web;

import com.example.course_app.entity.Administrator;
import com.example.course_app.entity.Course;
import com.example.course_app.service.AdministratorService;
import com.example.course_app.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administrators")
public class AdministratorWebController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String listAdministrators(Model model) {
        List<Administrator> administrators = administratorService.getAllAdministrators();
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("administrators", administrators);
        model.addAttribute("courses", courses);
        model.addAttribute("administrator", new Administrator());
        return "administrators";
    }

    @PostMapping
    public String createAdministrator(@ModelAttribute Administrator administrator, @RequestParam("course.id") Long courseId) {
        Course course = courseService.getCourseById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + courseId));
        administrator.setCourse(course);
        administratorService.createAdministrator(administrator);
        return "redirect:/administrators";
    }

    @GetMapping("/edit/{id}")
    public String editAdministrator(@PathVariable Long id, Model model) {
        Administrator administrator = administratorService.getAdministratorById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid administrator Id:" + id));
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("administrator", administrator);
        model.addAttribute("courses", courses);
        return "edit-administrator";
    }

    @PostMapping("/update/{id}")
    public String updateAdministrator(@PathVariable Long id, @ModelAttribute Administrator administrator, @RequestParam("course.id") Long courseId) {
        Course course = courseService.getCourseById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + courseId));
        administrator.setCourse(course);
        administratorService.updateAdministrator(id, administrator);
        return "redirect:/administrators";
    }

    @PostMapping("/delete/{id}")
    public String deleteAdministrator(@PathVariable Long id) {
        administratorService.deleteAdministrator(id);
        return "redirect:/administrators";
    }

}