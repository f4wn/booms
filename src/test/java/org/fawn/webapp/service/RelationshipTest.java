/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.service;

import java.util.ArrayList;
import java.util.List;
import org.fawn.webapp.entity.Book;
import org.fawn.webapp.entity.Category;
import org.fawn.webapp.entity.Publisher;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Fawn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/mvc-dispatcher-servlet.xml")
public class RelationshipTest {
    
    @Autowired
    private IBookService bookService;
    
    @Autowired
    private IPublisherService publisherService;
    
    @Autowired
    private ICategoryService categoryService;
    
    @Test
    public void testBookPublisherRelationship(){
        Book book = new Book();
        book.setIsbn("2015-999");
        book.setAuthor("John Simangunsong");
        book.setTitle("Batak Tribe");
        book.setYearPublished("2015");
        
        Publisher publisher = new Publisher();
        publisher.setId("5");
        publisher.setName("Jakarta Pustaka");
        publisher.setLocation("Jakarta");
        
        
        book.setPublisher(publisher);
        
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(book);
	publisher.setBookList(bookList);
        publisherService.addPublisher(publisher);
        bookService.addBook(book);
        
        Publisher persistedPublisher = publisherService.getPublisherById(publisher.getId());
        assertNotNull(persistedPublisher);
		//bookService.addBook(book);
        
        Book persistedBook = bookService.getBookByIsbn(book.getIsbn());
        assertNotNull(persistedBook);
        assertEquals(persistedBook.getPublisher().getId(), persistedPublisher.getId());
        assertEquals(persistedBook.getPublisher().getName(), persistedPublisher.getName());
        assertEquals(persistedBook.getPublisher().getLocation(), persistedPublisher.getLocation());
        
        //Try remove book, Publisher should not be cascaded
        publisher.setBookList(null);
        publisherService.updatePublisher(publisher);
        bookService.removeBook(book.getIsbn());
        
        persistedBook = bookService.getBookByIsbn(book.getIsbn());
        assertNull(persistedBook);
        persistedPublisher = publisherService.getPublisherById(publisher.getId());
        assertNotNull(persistedPublisher);
        
        //Reattach book, delete publisher should cascade delete the book
        bookService.addBook(book);
        publisher.setBookList(bookList);
        publisherService.updatePublisher(publisher);
        persistedBook = bookService.getBookByIsbn(book.getIsbn());
        assertNotNull(persistedBook);
        publisherService.removePublisher(persistedPublisher.getId());
        assertNull(publisherService.getPublisherById(persistedPublisher.getId()));
        assertNull(publisherService.getPublisherById(persistedBook.getIsbn()));
    }
    
    private final static Logger logger = (Logger) LoggerFactory.getLogger(RelationshipTest.class);
    
    @Test
    public void testBookCategoryRelationship(){
        Book book1 = new Book();
        book1.setIsbn("20140920-1030");
        book1.setAuthor("Book1 Author");
        book1.setTitle("Book1 Title");
        book1.setYearPublished("2014");
        
        Book book2 = new Book();
        book2.setIsbn("20140920-1040");
        book2.setAuthor("Book2 Author");
        book2.setTitle("Book2 Title");
        book2.setYearPublished("2014");
        
        Category category1 = new Category();
        category1.setName("History");
        category1.setDescription("History book");
        
        Category category2 = new Category();
        category2.setName("Art");
        category2.setDescription("Art book");
        
        categoryService.addCategory(category1);
        categoryService.addCategory(category2);
        
        book1.addCategoryIntoList(category1);
        book2.addCategoryIntoList(category1);
        book2.addCategoryIntoList(category2);
        
        bookService.addBook(book1);
        bookService.addBook(book2);
        
        category1.addBookIntoList(book1);
        category1.addBookIntoList(book2);
        category2.addBookIntoList(book2);
        categoryService.updateCategory(category1);
        categoryService.updateCategory(category2);
        
        //try remove category1 will update relation with book1 and book2
        category1.setBookList(null);
        categoryService.updateCategory(category1);
        categoryService.removeCategory(category1.getName());
        assertNull(categoryService.getCategoryByName(category1.getName()));
        assertNotNull(bookService.getBookByIsbn(book1.getIsbn()));
        assertTrue(bookService.getBookByIsbn(book1.getIsbn()).getCategoryList().isEmpty());
        assertNotNull(bookService.getBookByIsbn(book2.getIsbn()));
        
        logger.info(bookService.getBookByIsbn(book2.getIsbn()).getCategoryList().get(0).getName());
        assertTrue(bookService.getBookByIsbn(book2.getIsbn()).getCategoryList().size()==1);
        assertEquals(bookService.getBookByIsbn(book2.getIsbn()).getCategoryList().get(0).getName(), category2.getName());
        
        
    }
}
