package com.example.tabletguides;

import java.util.Map;

import com.example.tabletguides.entity.Category;
import com.example.tabletguides.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @Autowired
    private CategoryRepo categoryRepo;

    public GreetingController() {
    }

    @GetMapping("/edit")
    public String greeting(Map<String, Object> model) {
        Iterable<Category> categories = categoryRepo.findAll();
        model.put("categories", categories);
        return "edit";
    }

    @PostMapping("addNewCategory")
    public String addNewCategory(@RequestParam String name , Map<String, Object> model){
        System.out.println(name);
        Category category = new Category(name);
        categoryRepo.save(category);
        Iterable<Category> categories = categoryRepo.findAll();
        model.put("categories", categories);

        return "edit";
    }



    @GetMapping("/")
    public String main(Map<String, Object> model) {
        return "main";
    }
}
