/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.service;

import java.util.List;
import org.fawn.webapp.dao.ICategoryDAO;
import org.fawn.webapp.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fawn
 */
@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private ICategoryDAO categoryDAO;
    
    @Transactional
    @Override
    public void addCategory(Category category) {
        categoryDAO.addCategory(category);
    }
    
    @Transactional
    @Override
    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }

    @Transactional
    @Override
    public List<Category> getCategoryList() {
        return categoryDAO.getCategoryList();
    }

    @Transactional
    @Override
    public void removeCategory(String name) {
        categoryDAO.removeCategory(name);
    }

    @Transactional
    @Override
    public Category getCategoryByName(String name) {
        return categoryDAO.getCategoryByName(name);
    }
    
}
