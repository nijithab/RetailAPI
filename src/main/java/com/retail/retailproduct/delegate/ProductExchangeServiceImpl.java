package com.retail.retailproduct.delegate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.retailproduct.converter.ProductConverter;
import com.retail.retailproduct.domain.Product;
import com.retail.retailproduct.dto.Productdto;
import com.retail.retailproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductExchangeServiceImpl implements ProductExchangeService {
    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public ProductConverter productConverter;

    /**
     * Retrieves the product details by product id
     *
     * @param productId
     * @return Productdto
     * @throws Exception
     */
    @Override
    public Productdto getById(String productId) throws Exception {
        Productdto productdto = new Productdto();
        String productName = getProductNameFromExternalApi(productId);
        Product productEntity = productRepository.findByProductId(productId);
        if(productEntity !=  null) {
            productdto = productConverter.getProductdtoFromProductEntity(productEntity);
        }
        productdto.setName(productName);
        return productdto;
    }

    /**
     * Creates or Updates the product in database
     *
     * @param product
     * @return Productdto
     * @throws Exception
     */
    @Override
    public Productdto saveOrUpdate(Productdto product) throws Exception {
        Productdto productdto = new Productdto();
        Product productEntity  = productRepository.save(productConverter.getProductEntityFromProductdto(product));
        if(productEntity !=  null) {
            productdto = productConverter.getProductdtoFromProductEntity(productEntity);
        }
        return productdto;
    }

    /**
     * Retrieves the product name from External Target API by product Id
     * @param productId
     * @return
     * @throws Exception
     */
    private String getProductNameFromExternalApi(String productId) throws Exception{
        String productName;
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://redsky.target.com/v1/pdp/tcin/"+ productId +"?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", productId);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, urlVariables);
        ObjectMapper objMapper = new ObjectMapper();
        JsonNode rootNode = objMapper.readTree(response.getBody());
        productName = rootNode.findPath("product_description").findValue("title").asText();
        return productName;
    }

}
