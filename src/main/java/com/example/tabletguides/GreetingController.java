package com.example.tabletguides;

import java.util.Map;

import com.example.tabletguides.entity.Category;
import com.example.tabletguides.entity.Instruction;
import com.example.tabletguides.repos.CategoryRepo;
import com.example.tabletguides.repos.InstructionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private InstructionRepo instructionRepo;

    public GreetingController() {
    }

    @GetMapping("/edit")
    public String greeting(Map<String, Object> model) {
        Iterable<Category> categories = categoryRepo.findAll();
        model.put("categories", categories);
        return "edit";
    }

    @PostMapping("addNewCategory")
    public String addNewCategory(@RequestParam String namecat , Map<String, Object> model){
        Category category = new Category(namecat);
        categoryRepo.save(category);
        Iterable<Category> categories = categoryRepo.findAll();
        model.put("categories", categories);

        return "edit";
    }

    @PostMapping("save")
    public String save(@RequestParam String categoryinput, @RequestParam String head, @RequestParam String maintext, @RequestParam String tag,  Map<String, Object> model){
        Instruction instruction = new Instruction(head, maintext,"null", tag, categoryinput);
        instructionRepo.save(instruction);
        Iterable<Instruction> instructions = instructionRepo.findAll();
        model.put("instruction", instructions);
        return "main";
    }



    @GetMapping("/")
    public String main(Map<String, Object> model) {
        Iterable<Instruction> instructions = instructionRepo.findAll();

        for(Instruction s:instructions){
            System.out.println(s.getMain());
            s.getMain().replaceAll("&gt;",">");
            s.getMain().replaceAll("&lt;","<");
            System.out.println(s.getMain());
        }


        model.put("instruction", instructions);
        return "main";
    }
}
