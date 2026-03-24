package com.subramanyav.ecoaiengine.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.subramanyav.ecoaiengine.dto.CategoryRequest;
import com.subramanyav.ecoaiengine.dto.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;


@Service
public class CategoryService {

    @Autowired
    private final OpenAiService openAIService;

    public CategoryService(OpenAiService openAIService) {
        this.openAIService = openAIService;
    }

    public CategoryResponse generate(CategoryRequest request){
        String aiResponse = openAIService.generateCategory(
                request.getProductName(),
                request.getDescription()
        );

        System.out.println("AI RESPONSE:" + aiResponse);


        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(aiResponse, CategoryResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse AI response", e);
        }


    }
}
