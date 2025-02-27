package com.example.course_app.service;

import com.example.course_app.entity.Lecturer;
import com.example.course_app.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;

    public Lecturer createLecturer(Lecturer lecturer) {
        return lecturerRepository.save(lecturer);
    }

    public List<Lecturer> getAllLecturers() {
        return lecturerRepository.findAll();
    }

    public Optional<Lecturer> getLecturerById(Long id) {
        return lecturerRepository.findById(id);
    }

    public void deleteLecturer(Long id) {
        lecturerRepository.deleteById(id);
    }

    public Lecturer updateLecturer(Long id, Lecturer lecturerDetails) {
        Lecturer lecturer = lecturerRepository.findById(id).orElseThrow();
        lecturer.setFirstname(lecturerDetails.getFirstname());
        lecturer.setLastname(lecturerDetails.getLastname());
        lecturer.setEmail(lecturerDetails.getEmail());
        lecturer.setStaffnumber(lecturerDetails.getStaffnumber());
        return lecturerRepository.save(lecturer);
    }
}
