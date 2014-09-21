/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.controller;

import javax.validation.Valid;
import org.fawn.webapp.entity.Category;
import org.fawn.webapp.service.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    
    @Autowired
    private ICategoryService categoryService;
    
    @RequestMapping(value={"/addcategory","/editcategory","/deletecategory"},method= RequestMethod.GET)
    public String redirectPage(){
            logger.info("Invalid Page Access. Redirecting..");
            return "redirect:/category";
    }
    
    @RequestMapping(value="/addcategory",method= RequestMethod.POST)
    public String addCategory(@ModelAttribute("category") @Valid Category category,BindingResult bindingResult,ModelMap modelMap){
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("category", category);
            modelMap.addAttribute("categoryList", categoryService.getCategoryList());
            return "pages/category";
        }else{
            categoryService.addCategory(category);
            return "redirect:/category";
        }
    }
    
    @RequestMapping("/category")
    public String getBookList(ModelMap modelMap){
        modelMap.addAttribute("category", new Category());
        modelMap.addAttribute("categoryList", categoryService.getCategoryList());
        return "pages/category";
    }
    
    @RequestMapping("deletecategory/name={name}")
    public String removeCategory(@PathVariable("name") String name){
        categoryService.removeCategory(name);
        return "redirect:/category";
    }
    
    @RequestMapping("editcategory/name={name}")
    public String editCategory(@PathVariable("name") String name,ModelMap modelMap){
        Category category =categoryService.getCategoryByName(name);
        if(category!=null){
            modelMap.addAttribute("category", category);
            return "pages/editcategory";
        }else{
            logger.info("Category with name '"+name+"' can not be found. Redirecting..");
            return "redirect:/category";
        }
    }
    
    @RequestMapping(value="editcategory",method= RequestMethod.POST)
    public String updateBook(@ModelAttribute("book") Category category,BindingResult bindingResult){
        categoryService.updateCategory(category);
        return "redirect:/category";
    }
}
