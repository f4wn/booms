/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.controller;

import org.fawn.webapp.entity.Publisher;
import org.fawn.webapp.service.IPublisherService;
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
    
    @Autowired
    private IPublisherService publisherService;
    
    @RequestMapping(value="/addpublisher",method= RequestMethod.POST)
    public String addPublisher(@ModelAttribute("publisher") Publisher publisher,BindingResult bindingResult){
        publisherService.addPublisher(publisher);
        return "redirect:/publisher";
    }
    
    @RequestMapping("/publisher")
    public String getPublisherList(ModelMap modelMap){
        modelMap.addAttribute("publisher", new Publisher());
        modelMap.addAttribute("publisherList", publisherService.getPublisherList());
        
        /*if (publisherService.getPublisherList().isEmpty())
        logger.info("Kosong");
        else {
        logger.info(publisherService.getPublisherList().get(0).getTitle());
        modelMap.addAttribute("test",publisherService.getPublisherList().get(0).getTitle());
        }*/
        return "publisher";
    }
    
    @RequestMapping("deletepublisher/pubid={pubid}")
    public String removePublisher(@PathVariable("pubid") String pubid){
        publisherService.removePublisher(pubid);
        return "redirect:/publisher";
    }
    
    @RequestMapping("editpublisher/pubid={pubid}")
    public String editPublisher(@PathVariable("pubid") String pubid,ModelMap modelMap){
        modelMap.addAttribute("publisher", publisherService.getPublisherById(pubid));
        return "editpublisher";
    }
    
    @RequestMapping(value="editpublisher/commit",method= RequestMethod.POST)
    public String updatePublisher(@ModelAttribute("publisher") Publisher publisher,BindingResult bindingResult){
        publisherService.updatePublisher(publisher);
        return "redirect:/publisher";
    }
}
