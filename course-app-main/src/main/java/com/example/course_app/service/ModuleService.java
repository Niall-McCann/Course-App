package com.example.course_app.service;

import com.example.course_app.entity.Module;
import com.example.course_app.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;

    public Module createModule(Module module) {
        return moduleRepository.save(module);
    }

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Optional<Module> getModuleById(Long id) {
        return moduleRepository.findById(id);
    }

    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }

    public Module updateModule(Long id, Module moduleDetails) {
        Module module = moduleRepository.findById(id).orElseThrow();
        module.setTitle(moduleDetails.getTitle());
        module.setDescription(moduleDetails.getDescription());
        return moduleRepository.save(module);
    }
}

