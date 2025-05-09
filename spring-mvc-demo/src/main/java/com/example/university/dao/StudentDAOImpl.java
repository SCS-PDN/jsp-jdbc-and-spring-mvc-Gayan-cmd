package com.example.university.dao;

import com.example.university.model.Course;
import com.example.university.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Student getStudentByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM students WHERE email = ? AND password = ?";
        List<Student> students = jdbcTemplate.query(sql, new Object[]{email, password}, (rs, rowNum) -> {
            Student s = new Student();
            s.setStudentId(rs.getInt("student_id"));
            s.setName(rs.getString("name"));
            s.setEmail(rs.getString("email"));
            return s;
        });
        return students.isEmpty() ? null : students.get(0);
    }

    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM courses";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Course c = new Course();
            c.setCourseId(rs.getInt("course_id"));
            c.setName(rs.getString("name"));
            c.setInstructor(rs.getString("instructor"));
            c.setCredits(rs.getInt("credits"));
            return c;
        });
    }
}
