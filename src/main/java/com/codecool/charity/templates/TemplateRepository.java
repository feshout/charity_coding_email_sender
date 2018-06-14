package com.codecool.charity.templates;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends CrudRepository<Template, Integer> {

    Template findById(Long id);
    Iterable<Template> findAll();
    Template findTemplateByHeader(String header);
    Template deleteById(Long id);


}
