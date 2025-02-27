package com.example.course_app.TestingWeb.Service;

import com.example.course_app.entity.Student;
import com.example.course_app.repository.StudentRepository;
import com.example.course_app.service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTestFindById {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testFindStudentById() {

        Long studentId = 1L;
        Student student = new Student("Niall", "McCann", 1234);
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        Optional<Student> result = studentService.getStudentById(studentId);

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get().getStudentnumber()).isEqualTo(1234);
        Assertions.assertThat(result.get().getFirstname()).isEqualTo("Niall");
        Assertions.assertThat(result.get().getLastname()).isEqualTo("McCann");
    }
}