/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.utility;

import org.fawn.webapp.entity.Category;
import org.fawn.webapp.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
/**
 *
 * @author Fawn
 */
public class StringToCategoryConverter implements Converter<String, Category> {

    @Autowired
    private ICategoryService categoryService;
    
    @Override
    public Category convert(String name) {
        return categoryService.getCategoryByName(name);
    }
    
}
