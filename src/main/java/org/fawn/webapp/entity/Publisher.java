/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Fawn
 */
@Entity
@Table(name="publisher")
public class Publisher implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="id")
    private String id;
    
    @NotBlank
    @Column(name="name")
    private String publisherName;
    
    @Column(name="location")
    private String location;

    @OneToMany(mappedBy="publisher",cascade= CascadeType.REMOVE,fetch= FetchType.EAGER)
    private List<Book> bookList;
    
    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String name) {
        this.publisherName = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void addBookIntoList(Book book){
        if(this.bookList==null)
            this.bookList = new ArrayList<Book>();
        this.bookList.add(book);
    }
    
    public void removeBookFromList(Book book){
        this.bookList.remove(book);
    }
}
