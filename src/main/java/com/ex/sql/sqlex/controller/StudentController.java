package com.ex.sql.sqlex.controller;

import com.ex.sql.sqlex.model.CreateStudentRequest;
import com.ex.sql.sqlex.model.Student;
import com.ex.sql.sqlex.model.UpdateStudentRequest;
import com.ex.sql.sqlex.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody CreateStudentRequest request) {
        LOGGER.info("Received request to create student with name {}", request.name());
        Student student = studentService.createStudent(request.name());

        return ResponseEntity
                .created(URI.create("/students/" + student.id()))
                .body(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudentEmail(@Valid @RequestBody UpdateStudentRequest request) {
        LOGGER.info("Received request to update student with id {}", request.id());
        Student student = studentService.updateStudent(request.id(), request.email());

        return ResponseEntity
                .ok(student);
    }
}
