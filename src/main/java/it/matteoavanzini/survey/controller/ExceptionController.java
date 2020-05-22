package it.matteoavanzini.survey.controller;

import javax.xml.ws.http.HTTPException;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController implements ErrorController {
 
    @RequestMapping("/error")
    public String handleError() {
        return "error/error";
    }
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
}