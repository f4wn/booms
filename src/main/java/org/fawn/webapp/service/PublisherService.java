/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.service;

import java.util.List;
import org.fawn.webapp.dao.IPublisherDAO;
import org.fawn.webapp.entity.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fawn
 */
@Service
public class PublisherService implements IPublisherService{

    @Autowired
    private IPublisherDAO publisherDAO;
    
    @Transactional
    @Override
    public void addPublisher(Publisher publisher) {
        publisherDAO.addPublisher(publisher);
    }

    @Transactional
    @Override
    public List<Publisher> getPublisherList() {
        return publisherDAO.getPublisherList();
    }

    @Transactional
    @Override
    public void removePublisher(String id) {
        publisherDAO.removePublisher(id);
    }

    @Transactional
    @Override
    public Publisher getPublisherById(String id) {
        return publisherDAO.getPublisherById(id);
    }

    @Transactional
    @Override
    public void updatePublisher(Publisher publisher) {
        publisherDAO.updatePublisher(publisher);
    }
    
}
