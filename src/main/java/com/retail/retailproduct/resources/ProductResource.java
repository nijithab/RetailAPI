package com.retail.retailproduct.resources;

import com.retail.retailproduct.Exception.BadRequestException;
import com.retail.retailproduct.Exception.ProductNotFoundException;
import com.retail.retailproduct.delegate.ProductExchangeService;
import com.retail.retailproduct.dto.Productdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductResource {

    @Autowired
    public ProductExchangeService productExchangeService;

    /**
     * Gets product name from Traget Redsky and pricing details form Mongo db
     *
     * @param productId
     * @return Productdto
     * @throws ProductNotFoundException
     */
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = "application/json")
    public Productdto retrieveProduct(@PathVariable("id") String productId) throws ProductNotFoundException {
        Productdto productdto;
        try {
          productdto = productExchangeService.getById(productId);
        } catch(Exception e) {
            throw new ProductNotFoundException("Product not found");
        }
        return productdto;
    }

    /**
     * Creates or Updates product info in Mongo db
     *
     * @param productdto
     * @param productId
     * @return
     * @throws BadRequestException
     */
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateProduct(@RequestBody Productdto productdto, @PathVariable("id") String productId) throws BadRequestException {

        if(!productId.equalsIgnoreCase(productdto.getId())) {
            throw new BadRequestException("Product id in the url and request body doesn't match");
        }
        try {
            productExchangeService.saveOrUpdate(productdto);
            return ResponseEntity.ok(productdto);
        } catch(Exception e){
            throw new BadRequestException("Product not updated");
        }

    }
}
