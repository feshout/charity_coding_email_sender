package com.codecool.charity.templates;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/templates")
public class TemplateController {

    private TemplateServcie templateServcie;

    public TemplateController(TemplateServcie templateServcie){
        this.templateServcie = templateServcie;
    }

    @GetMapping("")
    public Iterable<Template> index(){
        return templateServcie.getAll();
    }

    @GetMapping(path = "/{id}")
    public Template getOne(@PathVariable int id){

        return templateServcie.getOne(id);
    }

    @PostMapping(path = "")
    public Template create(@RequestBody Template template){

        templateServcie.save(template);
        return template;
    }


}
