/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.dao;

import java.util.List;
import org.fawn.webapp.entity.Book;
import org.fawn.webapp.entity.Publisher;

/**
 *
 * @author Fawn
 */
public interface IPublisherDAO {
    
    public void addPublisher(Publisher publisher);
    
    public void updatePublisher(Publisher publisher);
    
    public List<Publisher> getPublisherList ();
    
    public void removePublisher(String id);
    
    public Publisher getPublisherById(String id);
    
}
