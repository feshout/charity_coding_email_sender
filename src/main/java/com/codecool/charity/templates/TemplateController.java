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

        model.addAttribute("templates", templateService.getAll());
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

    @PostMapping("/add")
    public String create(@ModelAttribute Template template, Model model){

        model.addAttribute("temp", template);

        templateService.save(template);
        return "temp/template";
    }


}
