/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.controller;

import org.fawn.webapp.entity.Category;
import org.fawn.webapp.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author Fawn
 */
@Controller
public class CategoryController {
    
    @Autowired
    private ICategoryService categoryService;
    
    @RequestMapping(value="/addcategory",method= RequestMethod.POST)
    public String addCategory(@ModelAttribute("category") Category category,BindingResult bindingResult){
        categoryService.addCategory(category);
        return "redirect:/category";
    }
    
    @RequestMapping("/category")
    public String getBookList(ModelMap modelMap){
        modelMap.addAttribute("category", new Category());
        modelMap.addAttribute("categoryList", categoryService.getCategoryList());
        /*if (bookService.getBookList().isEmpty())
        logger.info("Kosong");
        else {
        logger.info(bookService.getBookList().get(0).getTitle());
        modelMap.addAttribute("test",bookService.getBookList().get(0).getTitle());
        }*/
        return "category";
    }
    
    @RequestMapping("deletecategory/name={name}")
    public String removeCategory(@PathVariable("name") String name){
        categoryService.removeCategory(name);
        return "redirect:/category";
    }
    
    @RequestMapping("editcategory/name={name}")
    public String editCategory(@PathVariable("name") String name,ModelMap modelMap){
        modelMap.addAttribute("category", categoryService.getCategoryByName(name));
        return "editcategory";
    }
    
    @RequestMapping(value="editcategory/commit",method= RequestMethod.POST)
    public String updateBook(@ModelAttribute("book") Category category,BindingResult bindingResult){
        //logger.info(book.getIsbn());
        categoryService.updateCategory(category);
        return "redirect:/category";
    }
}
