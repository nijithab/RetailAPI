package com.retail.retailproduct.delegate;

import com.retail.retailproduct.dto.Productdto;

public interface ProductExchangeService {
    Productdto getById(String productId) throws Exception;
    Productdto saveOrUpdate(Productdto product) throws Exception;
}
