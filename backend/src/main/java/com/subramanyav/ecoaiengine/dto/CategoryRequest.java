package com.subramanyav.ecoaiengine.dto;

import lombok.Data;

@Data
public class CategoryRequest {
    private String productName;
    private String description;

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }
}
