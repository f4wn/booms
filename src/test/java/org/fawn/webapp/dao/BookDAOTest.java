/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.dao;

import org.fawn.webapp.entity.Book;
import static org.junit.Assert.*;
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
    
    @Test
    public void testAddBook(){
        Book book = new Book();
        book.setIsbn("2014-01");
        book.setAuthor("Josh Kaufman");
        book.setTitle("The Personal MBA");
        book.setYearPublished("2014");
        
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
}
