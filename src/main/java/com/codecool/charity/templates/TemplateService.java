package com.codecool.charity.templates;

public interface TemplateService {

    Template findOne(Long id);

    Iterable<Template> getAll();

    void save(Template template);
}
