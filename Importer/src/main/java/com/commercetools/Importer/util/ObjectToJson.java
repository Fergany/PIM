package com.commercetools.Importer.util;

import com.commercetools.Importer.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ObjectToJson {
    public static String convertToJson(Product product) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        String productJsonStr = mapper.writeValueAsString(product);
        return productJsonStr;
    }
}
