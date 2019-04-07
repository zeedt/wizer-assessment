package com.wizer.test.wizer.assessment.service;

import com.wizer.test.wizer.assessment.dto.request.CategoryRequestModel;
import com.wizer.test.wizer.assessment.entities.Category;
import com.wizer.test.wizer.assessment.exception.WizerException;
import com.wizer.test.wizer.assessment.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findCategoryById(Long id) {
        return categoryRepository.findTopById(id);
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findTopByName(name);
    }

    public void addCategory(CategoryRequestModel categoryRequestModel) throws WizerException {

        Category category = categoryRepository.findTopByName(categoryRequestModel.getName());
        if (category != null)
            throw new WizerException(String.format("Category with name %s already exist", category.getName()));

        category = Category.builder()
                .name(categoryRequestModel.getName())
                .description(categoryRequestModel.getDescription())
                .build();

        categoryRepository.save(category);

    }

    public List<Category> getAllCategories() {

        return categoryRepository.findByIdNotNullOrderByIdDesc();

    }

}

