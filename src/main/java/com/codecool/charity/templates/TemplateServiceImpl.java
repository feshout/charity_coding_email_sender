package com.codecool.charity.templates;


import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateServcie {

    private TemplateRepository templateRepository;

    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public Template getOne(int id) {

        Template temp = templateRepository.findById(id);

        JtwigTemplate template
                = JtwigTemplate.classpathTemplate("templates/template.html");
        JtwigModel model = JtwigModel.newModel();

        model.with("header", temp.getHeader());
        model.with("title", temp.getTitle());
        model.with("description", temp.getDescription());

        template.render(model);

        return temp;
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
