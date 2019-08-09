package com.commercetools.aggregator.repository;

import com.commercetools.aggregator.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AggregatorRepository extends CrudRepository<Product, String> {
    @Query("SELECT COUNT(product_id) \n" +
            "FROM Product \n" +
            "WHERE DATE(createdAt) = DATE(NOW())")
    Long countByCreatedAt(String createdAt);

    @Query("SELECT COUNT(product_id) \n" +
            "FROM Product \n" +
            "WHERE DATE(modifiedAt) = DATE(NOW())")
    Long countByModifiedAt(String modifiedAt);

    List<Product> findAll();
}
