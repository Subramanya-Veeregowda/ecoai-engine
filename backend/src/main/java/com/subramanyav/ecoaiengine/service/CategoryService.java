package com.subramanyav.ecoaiengine.service;


import com.subramanyav.ecoaiengine.dto.CategoryRequest;
import com.subramanyav.ecoaiengine.dto.CategoryResponse;
import org.springframework.stereotype.Service;
import java.util.Arrays;


@Service
public class CategoryService {
    public CategoryResponse generate(CategoryRequest request){
        CategoryResponse response = new CategoryResponse();

        String productName = request.getProductName().toLowerCase();

        if (productName.contains("toothbrush")) {
            response.setCategory("Personal Care");
            response.setSubCategory("Oral Care");
        }else if(productName.contains("bottle")){
            response.setCategory("Home & Kitchen");
            response.setSubCategory("Reusable Items");
        }else{
            response.setCategory("General");
            response.setSubCategory("Misc");
        }

        response.setTags(Arrays.asList("eco","sustainable","organic"));
        response.setSustainability(Arrays.asList("recyclable","biodegradable"));

        return response;
    }
}
