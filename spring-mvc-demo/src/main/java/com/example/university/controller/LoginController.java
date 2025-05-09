package com.example.university.controller;

import com.example.university.dao.StudentDAO;
import com.example.university.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               Model model,
                               HttpSession session) {
        Student student = studentDAO.getStudentByEmailAndPassword(email, password);
        if (student != null) {
            session.setAttribute("student", student); // store in session
            return "redirect:/courses"; // redirect to avoid resubmission
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }
    }
}
