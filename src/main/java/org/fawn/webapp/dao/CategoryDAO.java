/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.dao;

import java.util.List;
import org.fawn.webapp.entity.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fawn
 */
@Repository
public class CategoryDAO implements ICategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void addCategory(Category category) {
        sessionFactory.getCurrentSession().save(category);
    }

    @Override
    public List<Category> getCategoryList() {
        return sessionFactory.getCurrentSession().createQuery("from Category").list();
    }

    @Override
    public void removeCategory(String name) {
        Category category = getCategoryByName(name);
        
        if(category!=null)
        sessionFactory.getCurrentSession().delete(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        return (Category) sessionFactory.getCurrentSession().get(Category.class, name);
    }

    @Override
    public void updateCategory(Category category) {
        sessionFactory.getCurrentSession().update(category);
    }
    
}
