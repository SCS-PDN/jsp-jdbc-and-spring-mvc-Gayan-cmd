package com.example.university.controller;

import com.example.university.dao.StudentDAO;
import com.example.university.model.Course;
import com.example.university.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/courses")
    public String showCourses(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) return "redirect:/login";

        List<Course> courses = studentDAO.getAllCourses();
        model.addAttribute("student", student);
        model.addAttribute("courses", courses);
        return "courses";
    }

    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable int courseId, HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) return "redirect:/login";

        boolean success = studentDAO.registerStudentToCourse(student.getStudentId(), courseId);
        if (success) {
            model.addAttribute("message", "Successfully registered for the course!");
        } else {
            model.addAttribute("error", "You are already registered for this course.");
        }
        return "success";
    }
}
