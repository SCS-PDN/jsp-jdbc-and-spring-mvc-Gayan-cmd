package com.example.university.dao;

import com.example.university.model.Course;
import com.example.university.model.Student;

import java.util.List;

public interface StudentDAO {
    Student getStudentByEmailAndPassword(String email, String password);
    List<Course> getAllCourses();
    boolean registerStudentToCourse(int studentId, int courseId);
}
