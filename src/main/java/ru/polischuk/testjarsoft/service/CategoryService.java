package ru.polischuk.testjarsoft.service;

import ru.polischuk.testjarsoft.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> getById(Long id);
    String save(Category category);
    String delete(Category category);
    List<Category> getAll();
}
