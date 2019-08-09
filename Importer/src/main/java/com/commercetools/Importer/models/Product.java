package com.commercetools.Importer.models;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Product implements Serializable {
    public Product(String UUID, String name, String description, String provider, boolean available, String measurementUnits) {
        this.UUID = UUID;
        this.name = name;
        this.description = description;
        this.provider = provider;
        this.available = available;
        this.measurementUnits = measurementUnits;
    }

    private String UUID;
    private String name;
    private String description;
    private String provider;
    private boolean available;
    private String measurementUnits;
}
