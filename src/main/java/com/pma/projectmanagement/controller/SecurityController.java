package com.pma.projectmanagement.controller;
import com.pma.projectmanagement.dao.UserRepository;
import com.pma.projectmanagement.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "security/logout";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "security/dashboard";
    }
}
