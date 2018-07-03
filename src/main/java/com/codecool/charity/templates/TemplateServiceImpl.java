package com.codecool.charity.templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

        List<Template> templates = new ArrayList<>();
        templateRepository.findAll().forEach(templates::add);
        templates.sort(sortByDate);

        return templates;
    }

    public void save(Template template){

        if (templateRepository.findTemplateByHeader(template.getHeader()) == null){
            template.setUpdateDate(new Date());
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
        toUpdate.setUpdateDate(new Date());

        templateRepository.save(toUpdate);
    }

    public void remove(Long id){

        Template template = templateRepository.findById(id);
        templateRepository.delete(template);
    }

    private Comparator<Template> sortByDate = (o1, o2) -> {

        if (o1.getUpdateDate().before(o2.getUpdateDate())){
            return 1;
        } else if (o1.getUpdateDate().after(o2.getUpdateDate())){
            return -1;
        } else {
            return 0;
        }
    };




}
