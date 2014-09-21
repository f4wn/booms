/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.dao;
import org.fawn.webapp.entity.Publisher;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.test.context.ContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fawn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/mvc-dispatcher-servlet.xml")
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class PublisherDAOTest {
    
    @Autowired
    private IPublisherDAO publisherDAO;
    
    @Test
    public void testAddPublisher(){
        Publisher publisher = new Publisher();
        publisher.setId("1");
        publisher.setPublisherName("London Press");
        publisher.setLocation("London, UK");
        
        assertTrue(publisherDAO.getPublisherList().isEmpty());
        publisherDAO.addPublisher(publisher);
        assertTrue(publisherDAO.getPublisherList().size()>0);
        
        Publisher persistedPublisher = publisherDAO.getPublisherById(publisher.getId());
        assertEquals(publisher.getPublisherName(), persistedPublisher.getPublisherName());
        assertEquals(publisher.getLocation(), persistedPublisher.getLocation());
        
    }
    
    @Test
    public void testRemovePublisher(){
        Publisher publisher = new Publisher();
        publisher.setId("2");
        publisher.setPublisherName("New York Times");
        publisher.setLocation("NY, USA");
        
        publisherDAO.addPublisher(publisher);
        
        assertNotNull(publisherDAO.getPublisherById(publisher.getId()));
        publisherDAO.removePublisher(publisher.getId());
        assertNull(publisherDAO.getPublisherById(publisher.getId()));
        
        
    }
    
    @Test
    public void testUpdatePublisher(){
        Publisher publisher = new Publisher();
        publisher.setId("3");
        publisher.setPublisherName("Associated Press");
        publisher.setLocation("NY, US");
        
        publisherDAO.addPublisher(publisher);
        
        String oldLocation = publisher.getLocation();
        publisher.setLocation("Boston, US");
        
        publisherDAO.addPublisher(publisher);
        Publisher persistedPublisher = publisherDAO.getPublisherById(publisher.getId());
        assertNotSame(oldLocation, persistedPublisher.getLocation());
        assertEquals(publisher.getLocation(), persistedPublisher.getLocation());
        
    }
}
