package com.wizer.test.wizer.assessment.repositories;

import com.wizer.test.wizer.assessment.entities.Book;
import com.wizer.test.wizer.assessment.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Long> {

    Category findTopById(Long id);

    Category findTopByName(String name);

    List<Category> findByIdNotNullOrderByIdDesc();

}
