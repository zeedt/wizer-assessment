package com.wizer.test.wizer.assessment.service;


import com.wizer.test.wizer.assessment.dto.request.BookPerAuthorChartModel;
import com.wizer.test.wizer.assessment.dto.request.BookRequestModel;
import com.wizer.test.wizer.assessment.dto.request.BookSearchModel;
import com.wizer.test.wizer.assessment.dto.request.BookPerCategoryChartModel;
import com.wizer.test.wizer.assessment.entities.Book;
import com.wizer.test.wizer.assessment.entities.Category;
import com.wizer.test.wizer.assessment.exception.WizerException;
import com.wizer.test.wizer.assessment.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryService categoryService;


    public void addBook(BookRequestModel bookRequestModel) throws WizerException {

        Category category = categoryService.findCategoryByName(bookRequestModel.getCategoryName());

        if (category == null)
            throw new WizerException(String.format("Category with id %s not found in system", bookRequestModel.getCategoryName()));

        validateIsbnAndTitle(bookRequestModel);

        Book book = Book.builder()
                .category(category)
                .dateAdded(new Date())
                .title(bookRequestModel.getTitle())
                .author(bookRequestModel.getAuthor())
                .isbn(bookRequestModel.getIsbn())
                .build();

        bookRepository.save(book);
    }

    private void validateIsbnAndTitle(BookRequestModel bookRequestModel) throws WizerException {
        if (bookRepository.findTopByIsbn(bookRequestModel.getIsbn()) != null)
            throw new WizerException("A book has already been registered with the ISBN");
        if (bookRepository.findTopByTitle(bookRequestModel.getTitle()) != null)
            throw new WizerException("A book has already been registered with the title");
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Page getPageWithParameters(BookSearchModel bookSearchModel) {

        if (noFilterValue(bookSearchModel))
            return bookRepository.findAllByIdNotNull(PageRequest.of(bookSearchModel.getPageNo(), bookSearchModel.getPageSize(), Sort.Direction.DESC, "id"));

        return bookRepository.findAll((Specification<Book>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.and(getBookSearchPredicate(bookSearchModel, criteriaBuilder, root).toArray(new Predicate[0])),
                PageRequest.of(bookSearchModel.getPageNo(), bookSearchModel.getPageSize(), Sort.Direction.DESC, "id"));
    }

    private boolean noFilterValue(BookSearchModel bookSearchModel) {
        return (StringUtils.isEmpty(bookSearchModel.getAuthor()) && StringUtils.isEmpty(bookSearchModel.getCategoryName())
                && StringUtils.isEmpty(bookSearchModel.getIsbn()) && StringUtils.isEmpty(bookSearchModel.getTitle()));
    }

    private List<Predicate> getBookSearchPredicate(BookSearchModel bookSearchModel, CriteriaBuilder criteriaBuilder, Root root) {
        List<Predicate> predicateList = new ArrayList<>();

        if (!StringUtils.isEmpty(bookSearchModel.getTitle())) {
            predicateList.add(criteriaBuilder.like(root.get("title"), String.format("%%%s%%",bookSearchModel.getTitle())));
        }

        if (!StringUtils.isEmpty(bookSearchModel.getAuthor())) {
            predicateList.add(criteriaBuilder.like(root.get("author"), String.format("%%%s%%",bookSearchModel.getAuthor())));
        }

        if (!StringUtils.isEmpty(bookSearchModel.getIsbn())) {
            predicateList.add(criteriaBuilder.like(root.get("isbn"), String.format("%%%s%%",bookSearchModel.getIsbn())));
        }

        if (!StringUtils.isEmpty(bookSearchModel.getCategoryName())) {
            predicateList.add(criteriaBuilder.equal(root.get("category").get("name"), bookSearchModel.getCategoryName()));
        }
        return predicateList;
    }

    public BookPerCategoryChartModel getDashboardDetailsForBooksPerCategory() {
        List<Object[]> dashboardDetails = bookRepository.getDashboardDetailsForBooksPerCategory();
        BookPerCategoryChartModel bookPerCategoryChartModel = BookPerCategoryChartModel.builder().build();
        dashboardDetails.stream().forEach(detail -> {
            if (detail != null) {
                bookPerCategoryChartModel.getXAxisData().add((String) detail[1]);
                bookPerCategoryChartModel.getYAxisData().add((BigInteger) detail[0]);
            }
        });

        return bookPerCategoryChartModel;
    }

    public BookPerAuthorChartModel getDashboardDetailsForBooksPerAuthor() {
        List<Object[]> dashboardDetails = bookRepository.getDashboardDetailsForBooksPerAuthor();
        BookPerAuthorChartModel bookPerCategoryChartModel = BookPerAuthorChartModel.builder().build();
        dashboardDetails.stream().forEach(detail -> {
            if (detail != null) {
                bookPerCategoryChartModel.getXAxisData().add((String) detail[1]);
                bookPerCategoryChartModel.getYAxisData().add((Long) detail[0]);
            }
        });

        return bookPerCategoryChartModel;
    }

}
