package com.example.course_app.repository;

import com.example.course_app.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    // Additional query methods can be defined here
}