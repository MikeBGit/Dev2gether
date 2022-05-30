package com.pma.projectmanagement.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AppErrorController implements ErrorController {

    @GetMapping("/error")
    public  String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/error-404";
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value()){
                return "error/error-403";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                return "error/error-500";
            }
        }

        return "error/error";
    }


    public String getErrorPath(){
        return "/error";
    }

    @GetMapping("/errorMain")
    public String errorMain() {
        return "error/error";
    }

    @GetMapping("/error403")
    public String error403() {
        return "error/error-403";
    }

    @GetMapping("/error404")
    public String error404() {
        return "error/error-404";
    }

    @GetMapping("/error500")
    public String error500() {
        return "error/error-500";
    }

}