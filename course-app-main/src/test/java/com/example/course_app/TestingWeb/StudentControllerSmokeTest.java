package com.example.course_app.TestingWeb;

import com.example.course_app.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentControllerSmokeTest {

    @Autowired
    private StudentController testingController;

    @Test
    void contextLoads() throws Exception {
        assertThat(testingController).isNotNull();
    }
}
