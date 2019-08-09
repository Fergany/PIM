package com.commercetools.aggregator.controllers;

import com.commercetools.aggregator.models.Product;
import com.commercetools.aggregator.services.AggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("aggregator")
public class AggregatorController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @Autowired
    private AggregatorService service;

    @RequestMapping("/")
    public String home() {
        return "Hello from Aggregator Service running at port: " + env.getProperty("server.port");
    }


    @RequestMapping("/test-recieve")
    public String testRecievingMsg() {
        return "Hello from Aggregator Service running at port: " + env.getProperty("server.port");
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getGallery() {
        List<Product> products = service.listProducts();
        return products;
    }

    @RequestMapping(value = "/products/dailyStatistics/{day}", method = RequestMethod.GET)
    public String showDailyStatistics(@PathVariable("day") String day){
         return service.getDailyStatistics(day);
    }

}
