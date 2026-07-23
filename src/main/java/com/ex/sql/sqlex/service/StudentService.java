package com.ex.sql.sqlex.service;

import com.ex.sql.sqlex.exception.StudentNotFoundException;
import com.ex.sql.sqlex.model.Student;
import com.ex.sql.sqlex.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(String name) {
        LOGGER.info("Creating student with name {}", name);
        return studentRepository.save(name.trim());
    }

    @Transactional
    public Student updateStudent(long id, String email) {
        LOGGER.info("Updating student with email {}", email);
        return studentRepository.updateStudentEmail(id, email.trim())
                .orElseThrow(() -> new StudentNotFoundException(id));
    }
}
