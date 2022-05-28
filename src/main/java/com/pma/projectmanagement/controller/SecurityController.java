package com.pma.projectmanagement.controller;

import com.pma.projectmanagement.dao.UserAccountRepository;
import com.pma.projectmanagement.dao.UserRepository;
import com.pma.projectmanagement.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String register(Model model){

        User user = new User();

        model.addAttribute("user", user);

        return "security/register";
    }

    @PostMapping("/register/save")
    public String saveUSer(Model model, User user){

        user.setPassword(bCryptEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return "redirect:/";


    }
}
