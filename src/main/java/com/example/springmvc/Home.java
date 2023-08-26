package com.example.springmvc;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
    @RequestMapping("/")
        public String home(){
        return "index";
        }

    @RequestMapping("/home{name}")
    public String project(@PathVariable String name){
        System.out.println(name);
        return "hwome";
    }

}
