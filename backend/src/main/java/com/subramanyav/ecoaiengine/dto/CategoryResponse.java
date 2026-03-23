package com.subramanyav.ecoaiengine.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private String category;
    private String subCategory;
    private List<String> tags;
    private List<String> sustainability;

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setSustainability(List<String> sustainability) {
        this.sustainability = sustainability;
    }
}
