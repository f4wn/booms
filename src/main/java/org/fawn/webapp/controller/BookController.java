/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.controller;

import org.fawn.webapp.entity.Book;
import org.fawn.webapp.entity.Category;
import org.fawn.webapp.entity.Publisher;
import org.fawn.webapp.service.IBookService;
import org.fawn.webapp.service.ICategoryService;
import org.fawn.webapp.service.IPublisherService;
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
import java.util.*;

/**
 *
 * @author Fawn
 */
@Controller
public class BookController {
    
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    
    @Autowired
    private IBookService bookService;
    
    @Autowired
    private IPublisherService publisherService;
    
    @Autowired
    private ICategoryService categoryService;
    
    @RequestMapping(value="/")
    public String addMessage(ModelMap modelMap){
        modelMap.addAttribute("message","Fawn");
        return "index";
    }
    
    @RequestMapping(value="/addbook",method= RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book,BindingResult bindingResult){
        
        //Publisher publisher =publisherService.getPublisherById((String)bindingResult.getModel().get("publisher_id"));
        //book.setPublisher(publisher);
        bookService.addBook(book);
        
        Publisher publisher = book.getPublisher();
        publisher.addBookIntoList(book);
        publisherService.updatePublisher(publisher);
        
        List<Category> categoryList = book.getCategoryList();
        
        for (Category category : categoryList) {
            category.addBookIntoList(book);
            categoryService.updateCategory(category);
        }
        
        return "redirect:/book";
    }
    
    @RequestMapping("/book")
    public String getBookList(ModelMap modelMap){
        modelMap.addAttribute("book", new Book());
        modelMap.addAttribute("bookList", bookService.getBookList());
        modelMap.addAttribute("publisherList",publisherService.getPublisherList());
        modelMap.addAttribute("categoryList",categoryService.getCategoryList());
        
        /*if (bookService.getBookList().isEmpty())
        logger.info("Kosong");
        else {
        logger.info(bookService.getBookList().get(0).getTitle());
        modelMap.addAttribute("test",bookService.getBookList().get(0).getTitle());
        }*/
        return "book";
    }
    
    @RequestMapping("deletebook/id={isbn}")
    public String removeBook(@PathVariable("isbn") String isbn){
        bookService.removeBook(isbn);
        return "redirect:/book";
    }
    
    @RequestMapping("editbook/id={isbn}")
    public String editBook(@PathVariable("isbn") String isbn,ModelMap modelMap){
        Book book = bookService.getBookByIsbn(isbn);
        modelMap.addAttribute("book", book);
        modelMap.addAttribute("publisherList",publisherService.getPublisherList());
        
        /*List<Category> categoryList =categoryService.getCategoryList();
        Iterator<Category> it = categoryList.iterator();
        for (Category categorySelected : book.getCategoryList()) {
            
            while(it.hasNext()){
                Category category =it.next();
                if(category.getName().equals(categorySelected.getName()))
                    categoryList.remove(category);
            }
        }*/
        modelMap.addAttribute("categoryList",categoryService.getCategoryList());
        
        return "editbook";
    }
    
    @RequestMapping(value="editbook/commit",method= RequestMethod.POST)
    public String updateBook(@ModelAttribute("book") Book book,BindingResult bindingResult){
        logger.info(book.getIsbn());
        
        Book oldBook = bookService.getBookByIsbn(book.getIsbn());
        
        Publisher oldPublisher = oldBook.getPublisher();
        oldPublisher.removeBookFromList(book);
        publisherService.updatePublisher(oldPublisher);
        
        List<Category> oldCategoryList = oldBook.getCategoryList();
        for (Category category : oldCategoryList) {
            category.removeBookFromList(oldBook);
            categoryService.updateCategory(category);
        }
        
        bookService.updateBook(book);
        
        Publisher publisher = book.getPublisher();
        publisher.addBookIntoList(book);
        publisherService.updatePublisher(publisher);
        
        List<Category> categoryList = book.getCategoryList();
        
        for (Category category : categoryList) {
            category.addBookIntoList(book);
            categoryService.updateCategory(category);
        }
        return "redirect:/book";
    }
}
