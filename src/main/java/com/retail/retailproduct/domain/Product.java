package com.retail.retailproduct.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Product Entity
 */
@Data
@Document(collection="product")
public class Product {
    @Id
    public String productId;
    public String productName;
    public Map<String, String> current_price;
}
