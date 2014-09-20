/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fawn.webapp.utility;

import org.fawn.webapp.entity.Publisher;
import org.fawn.webapp.service.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Fawn
 */
public class StringToPublisherConverter implements Converter<String, Publisher> {

    @Autowired
    IPublisherService publisherService;
    
    @Override
    public Publisher convert(String publisherIdStr) {
        return publisherService.getPublisherById(publisherIdStr);
    }
    
}
