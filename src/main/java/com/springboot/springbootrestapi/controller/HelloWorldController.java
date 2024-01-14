package com.springboot.springbootrestapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller annotation for making the class as controller
//Adding ResponseBody annotation for returning the Java object as JSON response.
//RestController annotation -> combination of @Controller & @ResponseBody
@RestController
public class HelloWorldController {

    //HTTP GET annotation for handling incoming get request.
    @GetMapping("/home")
    public String helloWorld(){
        return "Hello Welcome to my first API home page";
    }

}
