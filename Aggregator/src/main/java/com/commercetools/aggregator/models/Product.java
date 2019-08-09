package com.commercetools.aggregator.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int product_id;

    @Column(name = "UUID")
    private String UUID;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Provider")
    private String provider;

    @Column(name = "Available")
    private boolean available;

    @Column(name = "MeasurementUnits")
    private String measurementUnits;

    @Column(name = "createdAt")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "modifiedAt")
    @UpdateTimestamp
    private Date modifiedAt;

    @Override
    public String toString() {
        return this.UUID.concat(" " + this.name).concat(" " + this.provider);
    }
}
