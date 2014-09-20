/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Fawn
 */
@Entity
@Table(name="category")
public class Category implements Serializable {
    @Id
    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;
    
    @ManyToMany(fetch= FetchType.EAGER, cascade= {CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinTable(name="category_book",joinColumns=@JoinColumn(name="isbn"),inverseJoinColumns=@JoinColumn(name="name"))
    private List<Book> bookList;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void addBookIntoList(Book book){
        if(this.bookList==null)
            this.bookList = new ArrayList<Book>();
        
        this.bookList.add(book);
    }
    
    public void removeBookFromList(Book book){
        if(this.bookList!=null)
            this.bookList.remove(book);
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
