package com.ex.sql.sqlex.repository;

import com.ex.sql.sqlex.model.Student;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

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
                        RETURNING id, name
                        """)
                .param("name", name)
                .query((resultSet, rowNum) -> new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                ))
                .single();
    }
}
