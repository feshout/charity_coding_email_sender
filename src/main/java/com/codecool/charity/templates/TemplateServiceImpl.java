package com.codecool.charity.templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class TemplateServiceImpl implements TemplateServcie {

    private TemplateRepository templateRepository;
    private TemplateEngine engine;

    @Autowired
    public TemplateServiceImpl(TemplateRepository templateRepository, TemplateEngine templateEngine) {
        this.templateRepository = templateRepository;
        this.engine = templateEngine;
    }

    public String getOne(int id) {

        Template template = templateRepository.findById(id);
        Context context = new Context();
        context.setVariable("header", template.getHeader());
        context.setVariable("title", template.getTitle());
        context.setVariable("description", template.getDescription());

        String body = engine.process("template", context);

        return body;
    }

    public Iterable<Template> getAll(){
        return templateRepository.findAll();
    }

    public void save(Template template){

        if (templateRepository.findTemplateByHeader(template.getHeader()) == null){
            templateRepository.save(template);
        } else {
            throw new IllegalArgumentException("Object already exists");
        }
    }
}
