package com.commercetools.aggregator.services;

import com.commercetools.aggregator.models.Product;
import com.commercetools.aggregator.repository.AggregatorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AggregatorService {

    private static Logger log = LoggerFactory.getLogger(AggregatorService.class);


    @Autowired
    AggregatorRepository repository;

    ObjectMapper mapper = new ObjectMapper();

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "products_msg_exchange_name"),
            value = @Queue(value = "msg_queue_name", durable = "true"),
            key = "All-Products-Routing-key-001"))
    public void receive(String productJson) {
        try {
            Product product = mapper.reader().forType(Product.class).readValue( productJson);
            repository.save(product);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    public String getDailyStatistics(String day){
           Long productCountByCreatedAt = repository.countByCreatedAt(day);
           Long productCountByModifiedAt = repository.countByModifiedAt(day);
            return "Products Created At " +  day + " : " + productCountByCreatedAt
                    + " \n"
                    + "Products Created At " +  day + " : " + productCountByModifiedAt;
    }

    public List<Product> listProducts(){
        List<Product> products = repository.findAll();
        return products;
    }

}
