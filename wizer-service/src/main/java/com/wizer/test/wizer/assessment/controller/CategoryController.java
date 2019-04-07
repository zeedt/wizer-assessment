package com.wizer.test.wizer.assessment.controller;

import com.wizer.test.wizer.assessment.dto.request.CategoryRequestModel;
import com.wizer.test.wizer.assessment.entities.Category;
import com.wizer.test.wizer.assessment.exception.WizerException;
import com.wizer.test.wizer.assessment.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin()
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private Logger logger = LoggerFactory.getLogger(CategoryController.class.getName());

    @PostMapping("/new")
    public void addNewCategory(@Validated @RequestBody CategoryRequestModel categoryRequestModel) throws WizerException {

        try {
            categoryService.addCategory(categoryRequestModel);
        } catch (WizerException e) {
            logger.error("Error occurred due to ", e);
            throw new WizerException(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<Category> getAllCategories() throws WizerException {

        try {
            return categoryService.getAllCategories();
        } catch (Exception e) {
            logger.error("Error occured due to ", e);
            throw new WizerException(e.getMessage());
        }
    }

}
