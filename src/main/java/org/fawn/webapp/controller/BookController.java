/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.controller;

import java.util.List;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestParam;
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
        return "index";
    }
    
    @RequestMapping(value={"/addbook","/editbook","/deletebook"},method= RequestMethod.GET)
    public String redirectPage(){
            logger.info("Invalid Page Access. Redirecting..");
            return "redirect:/book";
    }
    
    @RequestMapping(value="/addbook",method= RequestMethod.POST)
    public String addBook( @ModelAttribute("book") @Valid Book book,BindingResult bindingResult, ModelMap modelMap){
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("book", book);
            modelMap.addAttribute("bookList", bookService.getBookList());
            modelMap.addAttribute("publisherList",publisherService.getPublisherList());
            modelMap.addAttribute("categoryList",categoryService.getCategoryList());

            return "pages/book";
        }else{
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
        
    }
    
    @RequestMapping(value="/book",method= RequestMethod.POST)
    public String getFilteredBookList(@RequestParam("title") String title,ModelMap modelMap){
        logger.info("Searching book with title '"+title+"'..");
        List<Book> bookList = bookService.searchBooksByTitle(title);
        
        modelMap.addAttribute("book", new Book());
        modelMap.addAttribute("bookList", bookList);
        modelMap.addAttribute("publisherList",publisherService.getPublisherList());
        modelMap.addAttribute("categoryList",categoryService.getCategoryList());
        
        modelMap.addAttribute("searchResult", "Found "+bookList.size()+" Books with keyword : "+title);
        
        return "pages/book";
    }
    
    @RequestMapping("/book")
    public String getBookList(ModelMap modelMap){
        modelMap.addAttribute("book", new Book());
        modelMap.addAttribute("bookList", bookService.getBookList());
        modelMap.addAttribute("publisherList",publisherService.getPublisherList());
        modelMap.addAttribute("categoryList",categoryService.getCategoryList());
        
        return "pages/book";
    }
    
    @RequestMapping("deletebook/id={isbn}")
    public String removeBook(@PathVariable("isbn") String isbn){
        Book book = bookService.getBookByIsbn(isbn);
        if(book!=null){
            for (Category category : book.getCategoryList()) {
                category.removeBookFromList(book);
                categoryService.updateCategory(category);
            }
        }
        bookService.removeBook(isbn);
        return "redirect:/book";
    }
    
    @RequestMapping("editbook/id={isbn}")
    public String editBook(@PathVariable("isbn") String isbn,ModelMap modelMap){
        Book book = bookService.getBookByIsbn(isbn);
        if(book!=null){
            modelMap.addAttribute("book", book);
            modelMap.addAttribute("publisherList",publisherService.getPublisherList());
            modelMap.addAttribute("categoryList",categoryService.getCategoryList());

            return "pages/editbook";
        }else{
            logger.info("Book with isbn '"+isbn+"' can not be found. Redirecting..");
            return "redirect:/book";
        }
    }
    
    @RequestMapping(value="editbook",method= RequestMethod.POST)
    public String updateBook(@ModelAttribute("pages/book") Book book,BindingResult bindingResult){
        
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
