package com.example.course_app.web;

import com.example.course_app.entity.Course;
import com.example.course_app.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")  // Maps to "/courses"
public class CourseWebController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String listCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";  // Thymeleaf template located at "/templates/courses.html"
    }

    @PostMapping
    public String createCourse(@ModelAttribute Course course) {
        courseService.createCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/view/{id}")
    public String viewCourse(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));

        model.addAttribute("course", course);
        model.addAttribute("modules", course.getModules());  // Assuming a Course contains list of Modules
        model.addAttribute("students", course.getStudents());  // Assuming a Course contains list of Students

        return "view-course"; // Corresponds to the view course template
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id).orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        model.addAttribute("course", course);
        return "edit-course";  // name of the Thymeleaf template for editing
    }

    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable Long id, @ModelAttribute Course course, Model model) {
        courseService.updateCourse(id, course);
        return "redirect:/courses";
    }

    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}