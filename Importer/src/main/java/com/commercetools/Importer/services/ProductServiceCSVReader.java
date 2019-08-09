package com.commercetools.Importer.services;

import com.commercetools.Importer.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ProductServiceCSVReader implements ProductService{

    private static Logger log = LoggerFactory.getLogger(ProductServiceCSVReader.class);

    @Value("${products.source.path}")
    private String productsPath;

    @Override
    public List<Product> getProducts() {

        Pattern pattern = Pattern.compile(",");

        List<Product> products = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
            BufferedReader in = new BufferedReader(new FileReader(productsPath));
            products = in .lines().skip(1) .map(line -> {
                String[] x = pattern.split(line);
                    return new Product(x[0], x[1], x[2], x[3], Boolean.parseBoolean(x[4]), x[5]);
            }).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            log.info(e.getMessage());
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return products;
    }


}
