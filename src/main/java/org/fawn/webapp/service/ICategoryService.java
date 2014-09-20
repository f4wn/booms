/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.service;

import java.util.List;
import org.fawn.webapp.entity.Category;

/**
 *
 * @author Fawn
 */
public interface ICategoryService {
    
    public void addCategory(Category category);
    
    public void updateCategory(Category category);
    
    public List<Category> getCategoryList();
    
    public void removeCategory(String name);
    
    public Category getCategoryByName(String name);
}
