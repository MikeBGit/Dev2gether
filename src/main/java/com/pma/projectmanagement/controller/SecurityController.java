package com.pma.projectmanagement.controller;
import com.pma.projectmanagement.dao.UserRepository;
import com.pma.projectmanagement.entities.User;
import com.pma.projectmanagement.service.ProjectService;
import com.pma.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class SecurityController {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectService projectService;

    @GetMapping("/register")
    public String register(Model model){

        User user = new User();

        model.addAttribute("user", user);

        return "security/register";
    }

    @PostMapping("/register")
    public String saveUSer(Model model, @Valid @ModelAttribute("user") User user, BindingResult result){

        if (result.hasErrors()) {
            return "security/register";
        }

        user.setPassword(bCryptEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return "redirect:/dashboard";


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
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName()).get();
        model.addAttribute(user);

        model.addAttribute("projects", projectService.getProjectsByProjectOwner(user));

        return "security/dashboard";
    }
}
