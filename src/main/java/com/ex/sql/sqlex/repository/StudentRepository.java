package com.ex.sql.sqlex.repository;

import com.ex.sql.sqlex.model.Student;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StudentRepository {

    private final JdbcClient jdbcClient;

    public StudentRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Student save(String name) {
        return jdbcClient.sql("""
                        INSERT INTO student (name)
                        VALUES (:name)
                        RETURNING id, name, email
                        """)
                .param("name", name)
                .query(this::mapStudent)
                .single();
    }

    public Optional<Student> updateStudentEmail(long id, String email) {
        return jdbcClient.sql("""
                        UPDATE student
                        SET email = :email
                        WHERE id = :id
                        RETURNING id, name, email
                        """)
                .param("id", id)
                .param("email", email)
                .query(this::mapStudent)
                .optional();
    }

    private Student mapStudent(java.sql.ResultSet resultSet, int rowNum) throws java.sql.SQLException {
        return new Student(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("email")
        );
    }
}
