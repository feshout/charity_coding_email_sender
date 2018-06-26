package com.codecool.charity.templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {

    private TemplateRepository templateRepository;

    @Autowired
    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public Template findOne(Long id) {

        return templateRepository.findById(id);
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

    public void update(Long id, Template temp){

        Template toUpdate = templateRepository.findById(id);

        toUpdate.setHeader(temp.getHeader());
        toUpdate.setTitle(temp.getTitle());
        toUpdate.setDescription(temp.getDescription());

        templateRepository.save(toUpdate);
    }

    public void remove(Long id){

        Template template = templateRepository.findById(id);
        templateRepository.delete(template);
    }
}
