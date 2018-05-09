package com.retail.retailproduct.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Productdto {

    public String id;
    public String name;
    public Map<String, String> current_price;

}
