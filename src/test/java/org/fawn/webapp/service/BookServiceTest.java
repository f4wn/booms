/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.service;

import org.fawn.webapp.entity.Book;
import org.fawn.webapp.entity.Publisher;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;
import java.util.ArrayList;
import org.fawn.webapp.entity.Category;

/**
 *
 * @author Fawn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/mvc-dispatcher-servlet.xml")
public class BookServiceTest {
    
    @Autowired
    private IBookService bookService;
    
    @Autowired
    private IPublisherService publisherService;
    
    @Autowired
    private ICategoryService categoryService;
    
    private Publisher publisher;
    
    private List<Category> categoryList;
    
    @Before
    public void initTest(){
        publisher = new Publisher();
        publisher.setPublisherName("Dummy Book Publisher 1");
        publisher.setLocation("Dummy Publisher 1 Location");
        publisherService.addPublisher(publisher);
        
        categoryList = new ArrayList<Category>();
        Category category = new Category();
        category.setCategoryName("Dummy Book Category 1");
        category.setDescription("Dummy Book Category Description");
        categoryList.add(category);
        categoryService.addCategory(category);
    }
    
    @After
    public void postTest(){
        publisherService.removePublisher(publisher.getId());
        categoryService.removeCategory(categoryList.get(0).getCategoryName());
    }
    
    @Test
    public void testAddBook(){
        Book book = new Book();
        book.setIsbn("2014-01");
        book.setAuthor("Josh Kaufman");
        book.setTitle("The Personal MBA");
        book.setYearPublished("2014");
        book.setPublisher(publisher);
        book.setCategoryList(categoryList);
        
        assertNull(bookService.getBookByIsbn(book.getIsbn()));
        bookService.addBook(book);
        assertTrue(bookService.getBookList().size()>0);
        Book persistedBook = bookService.getBookByIsbn(book.getIsbn());
        assertEquals(book.getIsbn(), persistedBook.getIsbn());
        assertEquals(book.getAuthor(), persistedBook.getAuthor());
        assertEquals(book.getTitle(), persistedBook.getTitle());
        assertEquals(book.getYearPublished(), persistedBook.getYearPublished());
    }
    
    @Test
    public void testRemoveBook(){
        Book book = new Book();
        book.setIsbn("2013-01");
        book.setAuthor("Alex Warren");
        book.setTitle("The Dummy Book");
        book.setYearPublished("2013");
        book.setPublisher(publisher);
        book.setCategoryList(categoryList);
        
        bookService.addBook(book);
        
        assertNotNull(bookService.getBookByIsbn(book.getIsbn()));
        bookService.removeBook(book.getIsbn());
        assertNull(bookService.getBookByIsbn(book.getIsbn()));
    }
    
    @Test
    public void testUpdateBook(){
        Book book = new Book();
        book.setIsbn("201402-5678");
        book.setAuthor("Anonymous");
        book.setTitle("The Temporary");
        book.setYearPublished("2014");
        book.setPublisher(publisher);
        book.setCategoryList(categoryList);
        
        bookService.addBook(book);
        
        String oldTitle = book.getTitle();
        book.setTitle("The Permanent");
        
        bookService.updateBook(book);
        Book persistedBook = bookService.getBookByIsbn(book.getIsbn());
        assertNotSame(oldTitle, persistedBook.getTitle());
        assertEquals(book.getTitle(), persistedBook.getTitle());
        assertEquals(book.getIsbn(), persistedBook.getIsbn());
        assertEquals(book.getAuthor(), persistedBook.getAuthor());
        assertEquals(book.getYearPublished(), persistedBook.getYearPublished());
    }
}
