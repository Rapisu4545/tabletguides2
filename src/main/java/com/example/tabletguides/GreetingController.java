package com.example.tabletguides;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {
    public GreetingController() {
    }

    @GetMapping({"/edit"})
    public String greeting(Map<String, Object> model) {
        return "edit";
    }

    @GetMapping({"/"})
    public String main(Map<String, Object> model) {
        return "main";
    }
}
