/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.service;

import java.util.List;
import org.fawn.webapp.dao.IBookDAO;
import org.fawn.webapp.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fawn
 */
@Service
public class BookService implements IBookService {
    
    @Autowired
    private IBookDAO bookDAO;

    @Transactional
    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Transactional
    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Transactional
    @Override
    public void removeBook(String isbn) {
        bookDAO.removeBook(isbn);
    }

    @Transactional
    @Override
    public Book getBookByIsbn(String isbn) {
        return bookDAO.getBookByIsbn(isbn);
    }

    @Transactional
    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }
    
    
    
}
