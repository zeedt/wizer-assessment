package com.wizer.test.wizer.assessment.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter @NoArgsConstructor
public class CategoryRequestModel {

    @NotBlank(message = "Category name cannot be blank")
    private String name;

    @NotBlank(message = "Category description cannot be blank")
    private String description;

}
