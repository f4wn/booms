/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.service;

import org.fawn.webapp.entity.Category;
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
public class CategoryServiceTest {
    @Autowired
    ICategoryService categoryService;
    
    @Test
    public void testAddCategory(){
        Category category = new Category();
        category.setName("Business");
        category.setDescription("Business Book");
        
        assertNull(categoryService.getCategoryByName(category.getName()));
        categoryService.addCategory(category);
        assertTrue(categoryService.getCategoryList().size()>0);
        Category persistedCategory = categoryService.getCategoryByName(category.getName());
        assertEquals(category.getName(), persistedCategory.getName());
        assertEquals(category.getDescription(), persistedCategory.getDescription());
    }
    
    @Test
    public void testRemoveCategory(){
        Category category = new Category();
        category.setName("Science Fiction");
        category.setDescription("Science Fiction Book");
        
        categoryService.addCategory(category);
        
        assertNotNull(categoryService.getCategoryByName(category.getName()));
        categoryService.removeCategory(category.getName());
        assertNull(categoryService.getCategoryByName(category.getName()));
        
    }
    
    @Test
    public void testUpdateCategory(){
        Category category = new Category();
        category.setName("Finance");
        category.setDescription("Finance Book");
        
        categoryService.addCategory(category);
        
        String oldDescription = category.getDescription();
        category.setDescription("Finance and Economy Book");
        
        categoryService.updateCategory(category);
        Category persistedCategory = categoryService.getCategoryByName(category.getName());
        assertNotSame(oldDescription, persistedCategory.getDescription());
        assertEquals(category.getDescription(), persistedCategory.getDescription());
        
    }
}
