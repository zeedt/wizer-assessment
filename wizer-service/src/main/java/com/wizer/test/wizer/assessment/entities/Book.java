package com.wizer.test.wizer.assessment.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter(value = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Book {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    private Date dateAdded;

    @OneToOne
    private Category category;

    private String author;

    @Column(unique = true, nullable = false)
    private String isbn;

}
