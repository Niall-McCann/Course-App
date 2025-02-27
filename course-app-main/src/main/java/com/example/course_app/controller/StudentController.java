package com.example.course_app.controller;

import com.example.course_app.dto.StudentDTO;
import com.example.course_app.entity.Course;
import com.example.course_app.entity.Student;
import com.example.course_app.repository.CourseRepository;
import com.example.course_app.repository.StudentRepository;
import com.example.course_app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDTO) {
        Optional<Course> courseOpt = courseRepository.findById(studentDTO.getCourseId());
        if (courseOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // or throw an exception
        }
        Course course = courseOpt.get();

        Student student = new Student();
        student.setFirstname(studentDTO.getFirstname());
        student.setLastname(studentDTO.getLastname());
        student.setStudentnumber(studentDTO.getStudentnumber());
        student.setEmail(studentDTO.getEmail());
        student.setCourse(course);

        Student saveStudent = studentRepository.save(student);
        return ResponseEntity.ok(saveStudent);
    }

//    @PostMapping
//    public Student createStudent(@RequestBody Student student) {
//        return studentService.createStudent(student);
//    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id).orElseThrow();
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLecturer(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
