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
@Table(name="book")
public class Book implements Serializable {
    @Id
    //@GeneratedValue
    @Column(name="isbn")
    private String isbn;

    @Column(name="title")
    private String title;
    
    @Column(name="author")
    private String author;
    
    @Column(name="yearPublished")
    private String yearPublished;
    
    @ManyToOne//(cascade= {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    private Publisher publisher;
    
    @ManyToMany(fetch= FetchType.EAGER,mappedBy="bookList")
    //@JoinTable(name="book_category",joinColumns=@JoinColumn(name="name"),inverseJoinColumns=@JoinColumn(name="isbn"))
    private List<Category> categoryList;
    
    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }
    
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void addCategoryIntoList(Category category){
        if(this.categoryList==null)
            this.categoryList = new ArrayList<Category>();
        
        this.categoryList.add(category);
    }
    
    public void removeCategoryFromList(Category category){
        if(this.categoryList!=null)
            this.categoryList.remove(category);
    }
    
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    
}
