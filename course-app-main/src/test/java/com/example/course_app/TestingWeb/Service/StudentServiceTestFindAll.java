package com.example.course_app.TestingWeb.Service;

import com.example.course_app.entity.Student;
import com.example.course_app.repository.StudentRepository;
import com.example.course_app.service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTestFindAll {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testFindAllStudents() {

        Student student1 = new Student("Niall", "McCann", 1234);
        Student student2 = new Student("N", "McCann", 12345);
        Student student3 = new Student("Liam", "Donnelly", 12349);
        Student student4 = new Student("Conall", "Murphy", 1);
        Student student5 = new Student("Conor", "O'Toole", 12);
        Student student6 = new Student("Kevin", "Scullion", 1234);


        List<Student> studentList = Arrays.asList(student1,student2,student3,student4,student5,student6);

        when(studentRepository.findAll()).thenReturn(studentList);
        List<Student> result = studentService.getAllStudents();

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).hasSize(6);
        Assertions.assertThat(result.get(0).getStudentnumber()).isEqualTo(1234);
        Assertions.assertThat(result.get(1).getFirstname()).isEqualTo("N");
        Assertions.assertThat(result.get(2).getLastname()).isEqualTo("Donnelly");

        Assertions.assertThat(result.get(3).getStudentnumber()).isEqualTo(1);
        Assertions.assertThat(result.get(4).getFirstname()).isEqualTo("Conor");
        Assertions.assertThat(result.get(5).getLastname()).isEqualTo("Scullion");
    }
}