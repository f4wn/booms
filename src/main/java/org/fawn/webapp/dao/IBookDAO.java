/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.dao;

import java.util.List;
import org.fawn.webapp.entity.Book;

/**
 *
 * @author Fawn
 */
public interface IBookDAO {
    
    public void addBook(Book book);
    
    public void updateBook(Book book);
    
    public List<Book> getBookList();
    
    public void removeBook(String isbn);
    
    public Book getBookByIsbn(String isbn);
    
    public List<Book> searchBooksByTitle(String title);
}
