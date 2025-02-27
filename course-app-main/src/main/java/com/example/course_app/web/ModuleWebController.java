package com.example.course_app.web;

import com.example.course_app.entity.Course;
import com.example.course_app.entity.Lecturer;
import com.example.course_app.entity.Module;
import com.example.course_app.service.CourseService;
import com.example.course_app.service.LecturerService;
import com.example.course_app.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/modules")
public class ModuleWebController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String listModules(Model model) {
        List<Module> modules = moduleService.getAllModules();
        List<Lecturer> lecturers = lecturerService.getAllLecturers();
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("modules", modules);
        model.addAttribute("lecturers", lecturers);
        model.addAttribute("courses", courses);
        model.addAttribute("module", new Module());
        return "modules";
    }

    @PostMapping
    public String createModule(@ModelAttribute Module module,
                               @RequestParam("lecturer.id") Long lecturerId,
                               @RequestParam("course.id") Long courseId) {
        Lecturer lecturer = lecturerService.getLecturerById(lecturerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lecturer Id:" + lecturerId));
        Course course = courseService.getCourseById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + courseId));
        module.setLecturer(lecturer);
        module.setCourse(course);
        moduleService.createModule(module);
        return "redirect:/modules";
    }

    // Additional methods for edit, update, delete...
}