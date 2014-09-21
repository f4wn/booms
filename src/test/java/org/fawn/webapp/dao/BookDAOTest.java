/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.dao;

import java.util.ArrayList;
import java.util.List;
import org.fawn.webapp.entity.Book;
import org.fawn.webapp.entity.Category;
import org.fawn.webapp.entity.Publisher;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Fawn
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/mvc-dispatcher-servlet.xml"})
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class BookDAOTest {
    
    @Autowired
    private IBookDAO bookDAO;
    
    @Autowired
    private IPublisherDAO publisherDAO;
    
    @Autowired
    private ICategoryDAO categoryDAO;
    
    private Publisher publisher;
    
    private List<Category> categoryList;
    
    @Before
    public void initTest(){
        publisher = new Publisher();
        publisher.setPublisherName("Dummy Book Publisher 1");
        publisher.setLocation("Dummy Publisher 1 Location");
        publisherDAO.addPublisher(publisher);
        
        categoryList = new ArrayList<Category>();
        Category category = new Category();
        category.setCategoryName("Dummy Book Category 1");
        category.setDescription("Dummy Book Category Description");
        categoryList.add(category);
        categoryDAO.addCategory(category);
    }
    
    @After
    public void postTest(){
        publisherDAO.removePublisher(publisher.getId());
        categoryDAO.removeCategory(categoryList.get(0).getCategoryName());
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
        
        assertTrue(bookDAO.getBookList().isEmpty());
        bookDAO.addBook(book);
        assertTrue(bookDAO.getBookList().size()>0);
        Book persistedBook = bookDAO.getBookByIsbn(book.getIsbn());
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
        
        bookDAO.addBook(book);
        
        assertNotNull(bookDAO.getBookByIsbn(book.getIsbn()));
        bookDAO.removeBook(book.getIsbn());
        assertNull(bookDAO.getBookByIsbn(book.getIsbn()));
        
        
    }
    
    @Test
    public void testUpdateBook(){
        Book book = new Book();
        book.setIsbn("2014-02");
        book.setAuthor("Anonymous");
        book.setTitle("The Temporary");
        book.setYearPublished("2014");
        book.setPublisher(publisher);
        book.setCategoryList(categoryList);
        
        bookDAO.addBook(book);
        
        String oldTitle = book.getTitle();
        book.setTitle("The Permanent");
        
        bookDAO.addBook(book);
        Book persistedBook = bookDAO.getBookByIsbn(book.getIsbn());
        assertNotSame(oldTitle, persistedBook.getTitle());
        assertEquals(book.getTitle(), persistedBook.getTitle());
        assertEquals(book.getIsbn(), persistedBook.getIsbn());
        assertEquals(book.getAuthor(), persistedBook.getAuthor());
        assertEquals(book.getYearPublished(), persistedBook.getYearPublished());
    }
    
    @Test
    public void testSearchBook(){
        Book book1 = new Book();
        book1.setIsbn("2014-9991");
        book1.setAuthor("Anonymous 2");
        book1.setTitle("The Temporary Book");
        book1.setYearPublished("2015");
        book1.setPublisher(publisher);
        book1.setCategoryList(categoryList);
        
        bookDAO.addBook(book1);
        
        Book book2 = new Book();
        book2.setIsbn("2013-345464");
        book2.setAuthor("Alex Warren");
        book2.setTitle("The Dummy Book");
        book2.setYearPublished("2013");
        book2.setPublisher(publisher);
        book2.setCategoryList(categoryList);
        
        bookDAO.addBook(book2);
        
        //Search for book1
        assertEquals(book1.getTitle(),bookDAO.searchBooksByTitle("Temp").get(0).getTitle());
        //Search for book2
        assertEquals(book2.getTitle(),bookDAO.searchBooksByTitle("Dummy").get(0).getTitle());
        //Search both books
        assertTrue(bookDAO.searchBooksByTitle("The").size()==2);
    }
}
