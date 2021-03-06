package com.example.tabletguides;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    @Value("filename")
    private static List<String> noimages = new ArrayList<>();

    public GreetingController() {
    }

    @GetMapping("/new")
    public String greeting(Map<String, Object> model) {
        Iterable<Category> categories = categoryRepo.findAll();
        model.put("categories", categories);
        return "new";
    }

    @PostMapping("addNewCategory")
    public String addNewCategory(@RequestParam String namecat , Map<String, Object> model){
        Category category = new Category(namecat);
        categoryRepo.save(category);
        Iterable<Category> categories = categoryRepo.findAll();
        model.put("categories", categories);

        return "new";
    }

    @PostMapping("save")
    public String save(@RequestParam String categoryinput, @RequestParam String head, @RequestParam String maintext, @RequestParam String tag,  Map<String, Object> model){
        String imagetodb ="";
        String noimagetodb ="";
        if(!(images.isEmpty())){
            for (String s1:images){
                imagetodb = imagetodb+s1+";";
            }
            imagetodb = imagetodb.substring(0, imagetodb.length() - 1);
            images.clear();
        }

        if(!(noimages.isEmpty())){
            for (String s1:noimages){
                noimagetodb = noimagetodb+s1+";";
            }
            noimagetodb = noimagetodb.substring(0, noimagetodb.length() - 1);
            noimages.clear();
        }

        if (imagetodb==""){
            imagetodb=null;
        }

        if (noimagetodb==""){
            noimagetodb=null;
        }

        String fullhead = categoryinput+": "+head;
        Integer categoryid = categoryRepo.find(categoryinput);
        Instruction instruction = new Instruction(fullhead, maintext,imagetodb, noimagetodb, "<b>Теги: </b>"+tag.toLowerCase(), categoryid);
        instructionRepo.save(instruction);
        Iterable<Instruction> instructions = instructionRepo.findAllandSort();
        Iterable<Category> categories = categoryRepo.findAll();
        model.put("categories", categories);
        model.put("instruction", instructions);
        return "main";
    }

    @GetMapping("/save")
    public String saveGet(Map<String, Object> model){
        Iterable<Instruction> instructions = instructionRepo.findAllandSort();
        if (images!=null||!(images.isEmpty())){
            images.clear();
        }
        if (noimages!=null||!(noimages.isEmpty())){
            noimages.clear();
        }

        model.put("instruction", instructions);
        return "main";
        }

    @GetMapping("/")
    public String main(Map<String, Object> model) {
        Iterable<Instruction> instructions = instructionRepo.findAllandSort();
        Iterable<Category> categories = categoryRepo.findAll();

        if (images!=null||!(images.isEmpty())){
            images.clear();
        }
        if (noimages!=null||!(noimages.isEmpty())){
            noimages.clear();
        }
        model.put("categories", categories);
        model.put("instruction", instructions);
        return "main";
    }

    @PostMapping("uploadfiles")
    public String uploadfiles(@RequestParam(name="multifile", required=false, defaultValue = "null") Object multifile,  Map<String, Object> model) throws IOException {
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
                        if ((f.getOriginalFilename().contains("png")) | (f.getOriginalFilename().contains("jpg")) | (f.getOriginalFilename().contains("jpeg"))) {
                            images.add(resultFilename);
                        } else {
                            noimages.add(resultFilename);
                        }

                    }

            }

            else{
                if(!(((MultipartFile) multifile).getOriginalFilename().isEmpty())) {
                    File uploadDir = new File(uploadpath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }
                    String uuidFile = UUID.randomUUID().toString();
                    String resultFilename = uuidFile + "." + ((MultipartFile) multifile).getOriginalFilename();
                    if ((((MultipartFile) multifile).getOriginalFilename().contains("png")) | (((MultipartFile) multifile).getOriginalFilename().contains("jpg")) | (((MultipartFile) multifile).getOriginalFilename().contains("jpeg"))) {
                        images.add(resultFilename);
                    } else {
                        noimages.add(resultFilename);
                    }
                    ((MultipartFile) multifile).transferTo(new File(uploadpath + "/" + resultFilename));
                }
            }

        }
        Iterable<Category> categories = categoryRepo.findAll();
        model.put("categories", categories);
        model.put("images", images);
        model.put("noimage", noimages);

        return "new";
    }

    @PostMapping("delete")
    public String delete(@RequestParam String delete, Map<String, Object> model){
        Long id = Long.parseLong(delete.replaceAll("delete", ""));
        instructionRepo.deleteById(id);
        Iterable<Instruction> instructions = instructionRepo.findAllandSort();
        model.put("instruction", instructions);
        return "main";
    }

    @PostMapping("editing")
    public String edit(@RequestParam String edit, Map<String, Object> model){

        Long id = Long.parseLong(edit.replaceAll("edit", ""));
        Optional<Instruction> optional = instructionRepo.findById(id);
        Instruction instruction=null;
        if (optional.isPresent()){
            instruction = optional.get();
        }
        else {
            return "main";
        }
        if (instruction.getImages()!=null){
            images = new ArrayList<String>(Arrays.asList(instruction.getImages().split(";")));
        }
        if (instruction.getNoimages()!=null){
            noimages = new ArrayList<String>(Arrays.asList(instruction.getNoimages().split(";")));
        }

        Iterable<Category> categories = categoryRepo.findAll();
        model.put("categories", categories);
        model.put("instruction", instruction);
        model.put("images", images);
        model.put("noimage", noimages);
        return "editing";

    }

    @GetMapping("/editing")
    public String editGet(Map<String, Object> model){
        Iterable<Instruction> instructions = instructionRepo.findAllandSort();
        if (images!=null||!(images.isEmpty())){
            images.clear();
        }
        if (noimages!=null||!(noimages.isEmpty())){
            noimages.clear();
        }
        Iterable<Category> categories = categoryRepo.findAll();
        model.put("categories", categories);

        model.put("instruction", instructions);
        return "main";
    }

    @PostMapping("uploadfilesfromedit")
    public String uploadfilesfromedit(@RequestParam String id, @RequestParam(name="multifile", required=false, defaultValue = "null") Object multifile,  Map<String, Object> model) throws IOException {
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
                    if ((f.getOriginalFilename().contains("png")) | (f.getOriginalFilename().contains("jpg")) | (f.getOriginalFilename().contains("jpeg"))) {
                        images.add(resultFilename);
                    } else {
                        noimages.add(resultFilename);
                    }

                }

            }

            else{
                if(!(((MultipartFile) multifile).getOriginalFilename().isEmpty())) {
                    File uploadDir = new File(uploadpath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }
                    String uuidFile = UUID.randomUUID().toString();
                    String resultFilename = uuidFile + "." + ((MultipartFile) multifile).getOriginalFilename();
                    if ((((MultipartFile) multifile).getOriginalFilename().contains("png")) | (((MultipartFile) multifile).getOriginalFilename().contains("jpg")) | (((MultipartFile) multifile).getOriginalFilename().contains("jpeg"))) {
                        images.add(resultFilename);
                    } else {
                        noimages.add(resultFilename);
                    }
                    ((MultipartFile) multifile).transferTo(new File(uploadpath + "/" + resultFilename));
                }
            }

        }

        Long instId = Long.parseLong(id.replaceAll("id",""));

        Optional<Instruction> optional = instructionRepo.findById(instId);

        Instruction instruction=null;
        if (optional.isPresent()){
            instruction = optional.get();
        }
        else {
            return "main";
        }


        Iterable<Category> categories = categoryRepo.findAll();
        model.put("instruction", instruction);
        model.put("categories", categories);
        model.put("images", images);
        model.put("noimage", noimages);

        return "editing";
    }

    @PostMapping("saveedit")
    public String saveedit(@RequestParam String id, @RequestParam String categoryinput, @RequestParam String head, @RequestParam String maintext, @RequestParam String tag,  Map<String, Object> model) {
        Long instId = Long.parseLong(id.replaceAll("id",""));
        String imagetodb = "";
        String noimagetodb = "";
        if (!(images.isEmpty())) {
            for (String s1 : images) {
                imagetodb = imagetodb + s1 + ";";
            }
            imagetodb = imagetodb.substring(0, imagetodb.length() - 1);
            images.clear();
        }

        if (!(noimages.isEmpty())) {
            for (String s1 : noimages) {
                noimagetodb = noimagetodb + s1 + ";";
            }
            noimagetodb = noimagetodb.substring(0, noimagetodb.length() - 1);
            noimages.clear();
        }

        if (imagetodb==""){
            imagetodb=null;
        }

        if (noimagetodb==""){
            noimagetodb=null;
        }

        Optional<Instruction> optional = instructionRepo.findById(instId);

        Instruction instruction=null;
        if (optional.isPresent()){
            instruction = optional.get();
        }
        else {
            return "main";
        }
        instruction.setHead(head);
        instruction.setMain(maintext);
        instruction.setCategory(categoryRepo.find(categoryinput));
        instruction.setTag(tag);
        instruction.setImages(imagetodb);
        instruction.setNoimages(noimagetodb);
        instructionRepo.save(instruction);
        Iterable<Instruction> instructions = instructionRepo.findAllandSort();
        model.put("instruction", instructions);
        Iterable<Category> categories = categoryRepo.findAll();
        model.put("categories", categories);
        return "main";
    }

    @GetMapping("/saveedit")
    public String saveeditGet(Map<String, Object> model){
        Iterable<Instruction> instructions = instructionRepo.findAllandSort();
        if (images!=null||!(images.isEmpty())){
            images.clear();
        }
        if (noimages!=null||!(noimages.isEmpty())){
            noimages.clear();
        }

        Iterable<Category> categories = categoryRepo.findAll();
        model.put("categories", categories);
        model.put("instruction", instructions);
        return "main";
    }

    @PostMapping("search")
    public String search(@RequestParam String search , Map<String, Object> model) {
        System.out.println(search);
        System.out.println();
        if (search != null && !(search.isEmpty())) {
            Iterable<Instruction> instructions = instructionRepo.findAllandSort();
            ArrayList<Instruction> searchList = new ArrayList<>();

            for (Instruction inst : instructions) {
                String[] tags = inst.getTag().replace("<b>Теги: </b>","").split(";");
                for (String t : tags) {
                    System.out.println("Тег:"+t);
                    if (t.trim().equals(search)) {
                        searchList.add(inst);
                        break;
                    }
                }
            }
            Iterable<Category> categories = categoryRepo.findAll();
            model.put("categories", categories);
            model.put("instruction", searchList);
            return "main";
        }
        else{
            Iterable<Instruction> instructions = instructionRepo.findAllandSort();
            Iterable<Category> categories = categoryRepo.findAll();
            model.put("categories", categories);
            model.put("instruction", instructions);
            return "main";
        }

    }


}


