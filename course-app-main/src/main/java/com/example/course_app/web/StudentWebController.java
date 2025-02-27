package com.example.course_app.web;

import com.example.course_app.entity.Course;
import com.example.course_app.entity.Student;
import com.example.course_app.service.CourseService;
import com.example.course_app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("students", students);
        model.addAttribute("courses", courses);
        model.addAttribute("student", new Student());
        return "students";
    }

    @PostMapping
    public String createStudent(@ModelAttribute Student student, @RequestParam("course.id") Long courseId) {
        Course course = courseService.getCourseById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + courseId));
        student.setCourse(course);
        studentService.createStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("student", student);
        model.addAttribute("courses", courses);
        return "edit-student"; // Corresponding Thymeleaf template
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student, @RequestParam("course.id") Long courseId) {
        Course course = courseService.getCourseById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + courseId));
        student.setCourse(course);
        studentService.updateStudent(id, student);
        return "redirect:/students";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}