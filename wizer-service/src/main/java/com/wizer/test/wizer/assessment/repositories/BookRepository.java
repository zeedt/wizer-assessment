package com.wizer.test.wizer.assessment.repositories;

import com.wizer.test.wizer.assessment.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository <Book, Long>, JpaSpecificationExecutor {

    Page<Book> findAllByIdNotNull(Pageable pageable);

    Book findTopByTitle(String title);

    Book findTopByIsbn(String isbn);

    @Query(value = "select count(b.id), c.name from Category c LEFT OUTER JOIN Book b ON b.category_id = c.id group by c.name", nativeQuery = true)
    List<Object[]> getDashboardDetailsForBooksPerCategory();

    @Query(value = "select count(b.id), b.author from Book b group by b.author")
    List<Object[]> getDashboardDetailsForBooksPerAuthor();

}
