package com.codecool.charity.templates;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/templates")
public class TemplateController {

    private TemplateServcie templateServcie;

    public TemplateController(TemplateServcie templateServcie){
        this.templateServcie = templateServcie;
    }

    @GetMapping("/")
    public Iterable<Template> index(){
        return templateServcie.getAll();
    }

    @GetMapping(path = "/{id}")
    public String getOne(@PathVariable int id, Model model){

        Template template = templateServcie.getOne(id);

        model.addAttribute("template", template);

        return "template";
    }

    @GetMapping("/add")
    public String displayForm(Model model){

        Template template = new Template();
        model.addAttribute("template", template);

        return "create";
    }

    @PostMapping(path = "/add")
    public String create(@ModelAttribute Template template, Model model){

        model.addAttribute("template", template);

        templateServcie.save(template);
        return "template";
    }


}
