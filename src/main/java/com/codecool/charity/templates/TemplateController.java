package com.codecool.charity.templates;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/templates")
public class TemplateController {

    private TemplateService templateService;

    public TemplateController(TemplateService templateService){
        this.templateService = templateService;
    }

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("temps", templateService.getAll());
        return "temp/displayAll";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model){

        Template template = templateService.findOne(id);

        model.addAttribute("temp", template);

        return "temp/template";
    }

    @GetMapping("/add")
    public String displayForm(Model model){

        Template template = new Template();
        model.addAttribute("temp", template);

        return "temp/create";
    }

    @GetMapping("/edit/{id}")
    public String displayEditForm(@PathVariable Long id, Model model){

        Template toUpdate = templateService.findOne(id);
        Template template = new Template();
        model.addAttribute("temp", template);
        model.addAttribute("toUpdate", toUpdate);

        return "temp/update";
    }

    @PutMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Template temp, Model model){

        templateService.update(id, temp);
        model.addAttribute("temp", temp);

        return "temp/template";

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model){

        templateService.remove(id);

        return "redirect:/templates/";
    }


    @PostMapping("/add")
    public String create(@ModelAttribute Template template, Model model){

        model.addAttribute("temp", template);

        templateService.save(template);
        return "temp/template";
    }


}
