/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.service;

import org.fawn.webapp.entity.Publisher;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Fawn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/mvc-dispatcher-servlet.xml")
public class PublisherServiceTest {
    
    @Autowired
    private IPublisherService publisherService;
    
    @Test
    public void testAddPublisher(){
        Publisher publisher = new Publisher();
        publisher.setId("1");
        publisher.setPublisherName("London Press");
        publisher.setLocation("London, UK");
        
        assertNull(publisherService.getPublisherById(publisher.getId()));
        publisherService.addPublisher(publisher);
        assertTrue(publisherService.getPublisherList().size()>0);
        
        Publisher persistedPublisher = publisherService.getPublisherById(publisher.getId());
        assertEquals(publisher.getPublisherName(), persistedPublisher.getPublisherName());
        assertEquals(publisher.getLocation(), persistedPublisher.getLocation());
        
    }
    
    @Test
    public void testRemovePublisher(){
        Publisher publisher = new Publisher();
        publisher.setId("2");
        publisher.setPublisherName("New York Times");
        publisher.setLocation("NY, USA");
        
        publisherService.addPublisher(publisher);
        
        assertNotNull(publisherService.getPublisherById(publisher.getId()));
        publisherService.removePublisher(publisher.getId());
        assertNull(publisherService.getPublisherById(publisher.getId()));
        
        
    }
    
    @Test
    public void testUpdatePublisher(){
        Publisher publisher = new Publisher();
        publisher.setId("3");
        publisher.setPublisherName("Associated Press");
        publisher.setLocation("NY, US");
        
        publisherService.addPublisher(publisher);
        
        String oldLocation = publisher.getLocation();
        publisher.setLocation("Boston, US");
        
        publisherService.updatePublisher(publisher);
        Publisher persistedPublisher = publisherService.getPublisherById(publisher.getId());
        assertNotSame(oldLocation, persistedPublisher.getLocation());
        assertEquals(publisher.getLocation(), persistedPublisher.getLocation());
        
    }
    
}
