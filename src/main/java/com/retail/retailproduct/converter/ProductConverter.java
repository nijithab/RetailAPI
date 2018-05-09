package com.retail.retailproduct.converter;

import com.retail.retailproduct.domain.Product;
import com.retail.retailproduct.dto.Productdto;
import org.springframework.stereotype.Component;

/**
 * ProductConverter class helps to convert entity object into DTO object and vice-versa
 */
@Component
public class ProductConverter {

    /**
     * Converts product entity into product dto
     * @param product
     * @return Productdto
     */
    public Productdto getProductdtoFromProductEntity(Product product){
        Productdto productdto = new Productdto();
        productdto.setId(product.getProductId());
        productdto.setName(product.getProductName());
        productdto.setCurrent_price(product.getCurrent_price());
        return productdto;
    }

    /**
     * Converts product dto into product entity
     * @param productdto
     * @return Product
     */
    public Product getProductEntityFromProductdto(Productdto productdto){
        Product product = new Product();
        product.setProductId(productdto.getId());
        product.setProductName(productdto.getName());
        product.setCurrent_price(productdto.getCurrent_price());
        return product;
    }

}
