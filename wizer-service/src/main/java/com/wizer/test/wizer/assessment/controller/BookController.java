package com.wizer.test.wizer.assessment.controller;

import com.wizer.test.wizer.assessment.dto.request.BookPerAuthorChartModel;
import com.wizer.test.wizer.assessment.dto.request.BookRequestModel;
import com.wizer.test.wizer.assessment.dto.request.BookSearchModel;
import com.wizer.test.wizer.assessment.dto.request.BookPerCategoryChartModel;
import com.wizer.test.wizer.assessment.entities.Book;
import com.wizer.test.wizer.assessment.exception.WizerException;
import com.wizer.test.wizer.assessment.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@CrossOrigin()
public class BookController {

    @Autowired
    private BookService bookService;

    private Logger logger = LoggerFactory.getLogger(BookController.class.getName());

    @PostMapping("/new")
    public void addBook(@Validated @RequestBody BookRequestModel bookRequestModel) throws WizerException {

        try {
            bookService.addBook(bookRequestModel);
        } catch (WizerException e) {
            logger.error("Error occurred due to ", e);
            throw new WizerException(e.getMessage());
        }

    }

    @GetMapping("/all")
    public List<Book> getAllBooks() throws WizerException {

        try {
            return bookService.getAllBooks();
        } catch (Exception e) {
            logger.error("Error occurred due to ", e);
            throw new WizerException(e.getMessage());
        }
    }


    @PostMapping("/get-page-with-parameters")
    public Page<Book> getPageWithParameters(@RequestBody BookSearchModel bookSearchModel) throws WizerException {

        try {
            return bookService.getPageWithParameters(bookSearchModel);
        } catch (Exception e) {
            logger.error("Error occurred due to ", e);
            throw new WizerException(e.getMessage());
        }
    }

    @GetMapping("/get-dashboard-details-for-books-per-category")
    public BookPerCategoryChartModel getDashboardDetailsForBooksPerCategory() throws WizerException {

        try {
            return bookService.getDashboardDetailsForBooksPerCategory();
        } catch (Exception e) {
            logger.error("Error occurred due to ", e);
            throw new WizerException(e.getMessage());
        }
    }

    @GetMapping("/get-dashboard-details-for-books-per-author")
    public BookPerAuthorChartModel getDashboardDetailsForBooksPerAuthor() throws WizerException {

        try {
            return bookService.getDashboardDetailsForBooksPerAuthor();
        } catch (Exception e) {
            logger.error("Error occurred due to ", e);
            throw new WizerException(e.getMessage());
        }
    }

}
