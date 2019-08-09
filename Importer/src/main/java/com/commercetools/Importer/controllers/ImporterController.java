package com.commercetools.Importer.controllers;

import com.commercetools.Importer.models.Product;
import com.commercetools.Importer.services.ProductServiceCSVReader;
import com.commercetools.Importer.util.ObjectToJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("importer")
public class ImporterController {

    @Autowired
    ProductServiceCSVReader productServiceCSVReader;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static Logger log = LoggerFactory.getLogger(ImporterController.class);

    @GetMapping("/test-send")
    public void sendMsgToExchange() {
        rabbitTemplate.convertAndSend(com.commercetools.Importer.config.RabbitMQConfig.EXCHANGE_NAME, "Routing-key-001", "Hello World!!!");
    }

    @PostConstruct
    public void getProducts() {
        List<Product> products = productServiceCSVReader.getProducts();
        Iterator<Product> productsIterator = products.iterator();
        while (productsIterator.hasNext()) {
            Product product = productsIterator.next();
            try {
                System.out.println(ObjectToJson.convertToJson(product));
                rabbitTemplate.convertAndSend(com.commercetools.Importer.config.RabbitMQConfig.EXCHANGE_NAME, "All-Products-Routing-key-001", ObjectToJson.convertToJson(product));
            } catch (IOException e) {
                log.error( e.getMessage() );
            }
        }

    }

}
