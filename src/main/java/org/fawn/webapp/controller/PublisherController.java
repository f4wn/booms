/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.controller;

import javax.validation.Valid;
import org.fawn.webapp.entity.Publisher;
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
/**
 *
 * @author Fawn
 */
@Controller
public class PublisherController {
    private static final Logger logger = LoggerFactory.getLogger(PublisherController.class);
    
    @Autowired
    private IPublisherService publisherService;
    
    @RequestMapping(value={"/addpublisher","/editpublisher","/deletepublisher"},method= RequestMethod.GET)
    public String redirectPage(){
            logger.info("Invalid Page Access. Redirecting..");
            return "redirect:/publisher";
    }
    
    @RequestMapping(value="/addpublisher",method= RequestMethod.POST)
    public String addPublisher(@ModelAttribute("publisher") @Valid Publisher publisher,BindingResult bindingResult,ModelMap modelMap){
        
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("publisher", publisher);
            modelMap.addAttribute("publisherList", publisherService.getPublisherList());

            return "pages/publisher";
        }else{
            publisherService.addPublisher(publisher);
            return "redirect:/publisher";
        }
    }
    
    @RequestMapping("/publisher")
    public String getPublisherList(ModelMap modelMap){
        modelMap.addAttribute("publisher", new Publisher());
        modelMap.addAttribute("publisherList", publisherService.getPublisherList());
        
        return "pages/publisher";
    }
    
    @RequestMapping("deletepublisher/pubid={pubid}")
    public String removePublisher(@PathVariable("pubid") String pubid){
        publisherService.removePublisher(pubid);
        return "redirect:/publisher";
    }
    
    @RequestMapping("editpublisher/pubid={pubid}")
    public String editPublisher(@PathVariable("pubid") String pubid,ModelMap modelMap){
        Publisher publisher = publisherService.getPublisherById(pubid);
        if(publisher!=null){
            modelMap.addAttribute("publisher", publisher);
            return "pages/editpublisher";
        }else{
            logger.info("Publisher with id '"+pubid+"' can not be found. Redirecting..");
            return "redirect:/publisher";
        }
    }
    
    @RequestMapping(value="editpublisher",method= RequestMethod.POST)
    public String updatePublisher(@ModelAttribute("publisher") Publisher publisher,BindingResult bindingResult){
        publisherService.updatePublisher(publisher);
        return "redirect:/publisher";
    }
}
