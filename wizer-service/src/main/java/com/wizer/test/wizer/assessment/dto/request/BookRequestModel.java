package com.wizer.test.wizer.assessment.dto.request;

import com.wizer.test.wizer.assessment.entities.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter @NoArgsConstructor
public class BookRequestModel {

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 3, message = "Author's name cannot be less than 2 characters")
    private String title;

    @NotBlank(message = "Category name cannot be blank")
    private String categoryName;

    @NotBlank(message = "isbn cannot be blank")
    @Size(min = 10, message = "Author's name cannot be less than 10 characters")
    private String isbn;

    @NotBlank(message = "author cannot be blank")
    @Size(min = 3, message = "Author's name cannot be less than 3 characters")
    private String author;

}
