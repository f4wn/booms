/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.dao;

import java.util.List;
import org.fawn.webapp.entity.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fawn
 */
@Repository
public class BookDAO implements IBookDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addBook(Book book) {
        sessionFactory.getCurrentSession().save(book);
    }

    @Override
    public List<Book> getBookList() {
        return sessionFactory.getCurrentSession().createQuery("from Book").list();
    }

    @Override
    public void removeBook(String isbn) {
        Book book = getBookByIsbn(isbn);
        if(book != null)
        sessionFactory.getCurrentSession().delete(book);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return (Book) sessionFactory.getCurrentSession().get(Book.class, isbn);
    }

    @Override
    public void updateBook(Book book) {
        sessionFactory.getCurrentSession().update(book);
    }
    
}
