/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.dao;

import org.fawn.webapp.entity.Category;
import org.springframework.test.context.ContextConfiguration;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 *
 * @author Fawn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/mvc-dispatcher-servlet.xml"})
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class CategoryDAOTest {
    
    @Autowired
    ICategoryDAO categoryDAO;
    
    @Test
    public void testAddCategory(){
        Category category = new Category();
        category.setCategoryName("Business");
        category.setDescription("Business Book");
        
        assertTrue(categoryDAO.getCategoryList().isEmpty());
        categoryDAO.addCategory(category);
        assertTrue(categoryDAO.getCategoryList().size()>0);
        Category persistedCategory = categoryDAO.getCategoryByName(category.getCategoryName());
        assertEquals(category.getCategoryName(), persistedCategory.getCategoryName());
        assertEquals(category.getDescription(), persistedCategory.getDescription());
    }
    
    @Test
    public void testRemoveCategory(){
        Category category = new Category();
        category.setCategoryName("Science Fiction");
        category.setDescription("Science Fiction Book");
        
        categoryDAO.addCategory(category);
        
        assertNotNull(categoryDAO.getCategoryByName(category.getCategoryName()));
        categoryDAO.removeCategory(category.getCategoryName());
        assertNull(categoryDAO.getCategoryByName(category.getCategoryName()));
        
        
    }
    
    @Test
    public void testUpdateCategory(){
        Category category = new Category();
        category.setCategoryName("Finance");
        category.setDescription("Finance Book");
        
        categoryDAO.addCategory(category);
        
        String oldDescription = category.getDescription();
        category.setDescription("Finance and Economy Book");
        
        categoryDAO.addCategory(category);
        Category persistedCategory = categoryDAO.getCategoryByName(category.getCategoryName());
        assertNotSame(oldDescription, persistedCategory.getDescription());
        assertEquals(category.getDescription(), persistedCategory.getDescription());
        
    }
}
