package com.example.tabletguides;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.tabletguides.entity.Category;
import com.example.tabletguides.entity.Instruction;
import com.example.tabletguides.repos.CategoryRepo;
import com.example.tabletguides.repos.InstructionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GreetingController {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private InstructionRepo instructionRepo;

    @Value("${upload.path}")
    private String uploadpath;

    @Value("filename")
    private static List<String> images = new ArrayList<>();

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
        images.clear();
        return "main";
    }



    @GetMapping("/")
    public String main(Map<String, Object> model) {
        Iterable<Instruction> instructions = instructionRepo.findAll();

        model.put("instruction", instructions);
        return "main";
    }

    @PostMapping("uploadfiles")
    public String uploadfiles(@RequestParam(name="multifile", required=false) Object multifile,  Map<String, Object> model) throws IOException {
        if (multifile!=null){
            if (multifile instanceof Iterable) {
                File uploadDir = new File(uploadpath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                for (MultipartFile f : (Iterable<MultipartFile>) multifile) {
                    String uuidFile = UUID.randomUUID().toString();
                    String resultFilename = uuidFile + "." + f.getOriginalFilename();
                    f.transferTo(new File(uploadpath + "/" + resultFilename));
                    images.add(resultFilename);
                }
            }

            else{
                    File uploadDir = new File(uploadpath);
                    if(!uploadDir.exists()){
                        uploadDir.mkdir();
                    }
                    String uuidFile = UUID.randomUUID().toString();
                    String resultFilename = uuidFile+"."+((MultipartFile)multifile).getOriginalFilename();
                images.add(resultFilename);
                ((MultipartFile)multifile).transferTo(new File(uploadpath+"/"+resultFilename));
            }

            for(String s: images){
                System.out.println(s);
            }
            System.out.println();

        }
        Iterable<Category> categories = categoryRepo.findAll();
        model.put("categories", categories);
        model.put("images", images);

        return "edit";
    }
}
