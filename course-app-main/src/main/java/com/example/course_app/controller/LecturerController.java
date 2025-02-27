package com.example.course_app.controller;

import com.example.course_app.entity.Lecturer;
import com.example.course_app.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecturers")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @PostMapping
    public Lecturer createLecturer(@RequestBody Lecturer lecturer) {
        return lecturerService.createLecturer(lecturer);
    }

    @GetMapping
    public List<Lecturer> getAllLecturers() {
        return lecturerService.getAllLecturers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lecturer> getLecturerById(@PathVariable Long id) {
        Lecturer lecturer = lecturerService.getLecturerById(id).orElseThrow();
        return ResponseEntity.ok(lecturer);
    }

    @PutMapping("/{id}")
    public Lecturer updateLecturer(@PathVariable Long id, @RequestBody Lecturer lecturer) {
        return lecturerService.updateLecturer(id, lecturer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLecturer(@PathVariable Long id) {
        lecturerService.deleteLecturer(id);
        return ResponseEntity.noContent().build();
    }
}
