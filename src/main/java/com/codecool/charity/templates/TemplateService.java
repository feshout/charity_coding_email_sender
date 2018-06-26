package com.codecool.charity.templates;

public interface TemplateService {

    Template findOne(Long id);

    Iterable<Template> getAll();

    void save(Template template);

    void update(Long id, Template temp);

    void remove(Long id);
}
