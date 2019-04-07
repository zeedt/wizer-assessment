package com.wizer.test.wizer.assessment.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class BookSearchModel {

    private String author;

    private String title;

    private String isbn;

    private String categoryName;

    private Integer pageNo = 0;

    private Integer pageSize = 10;

}
