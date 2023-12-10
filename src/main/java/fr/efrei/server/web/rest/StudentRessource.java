package fr.efrei.server.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentRessource {
    @GetMapping("/students")
    public String test(){
        return "Hello World !";
    }
}