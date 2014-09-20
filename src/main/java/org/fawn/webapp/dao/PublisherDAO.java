/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.dao;

import java.util.List;
import org.fawn.webapp.entity.Publisher;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Fawn
 */
@Repository
public class PublisherDAO implements IPublisherDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void addPublisher(Publisher publisher) {
        sessionFactory.getCurrentSession().save(publisher);
    }

    @Override
    public List<Publisher> getPublisherList() {
        return sessionFactory.getCurrentSession().createQuery("from Publisher").list();
    }

    @Override
    public void removePublisher(String id) {
        Publisher publisher = getPublisherById(id);
        
        if(publisher!=null)
        sessionFactory.getCurrentSession().delete(publisher);
    }

    @Override
    public Publisher getPublisherById(String id) {
        return (Publisher)sessionFactory.getCurrentSession().get(Publisher.class, id);
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        sessionFactory.getCurrentSession().update(publisher);
    }
    
}
