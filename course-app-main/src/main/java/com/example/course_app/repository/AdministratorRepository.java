package com.example.course_app.repository;

import com.example.course_app.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    // Additional query methods can be defined here
}
