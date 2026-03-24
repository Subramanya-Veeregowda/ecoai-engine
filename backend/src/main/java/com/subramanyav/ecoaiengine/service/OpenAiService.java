package com.subramanyav.ecoaiengine.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


import java.time.Duration;
import java.util.List;

@Service
public class OpenAiService {

    @Value("${OPENAI_API_KEY}")
    private String apiKey;

    @Value("${ai.mock.enabled:true}")
    private boolean useMock;

    private String getMockResponse(){
        return """
            {
              "category": "Household",
              "subcategory": "Plastic Containers",
              "tags": ["plastic", "reusable"],
              "sustainability": ["low"]
            }
            """;
    }

    private String callOpenAI(String product, String description){
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);

        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = """
                    {
                      "model": "gpt-4o-mini",
                      "messages": [
                        {"role": "user", "content": "Classify product: %s Description: %s"}
                      ]
                    }
        """.formatted(product, description);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            System.out.println("Calling OpenAI...");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            ResponseEntity<String> response =
                    restTemplate.postForEntity(url, entity, String.class);

            System.out.println("✅ Response received");

            return response.getBody();
        } catch (HttpClientErrorException.TooManyRequests e) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Rate limit exceeded. Try again later.");
        } catch (Exception e) {
            throw new RuntimeException("❌ OpenAI call failed: " + e.getMessage());

        }
    }

    public String generateCategory(String product, String description) {

        if(useMock){
            return getMockResponse();
        }

        return callOpenAI(product, description);

    }
}