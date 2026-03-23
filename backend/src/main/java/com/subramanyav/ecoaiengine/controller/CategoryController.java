package com.subramanyav.ecoaiengine.controller;

import com.subramanyav.ecoaiengine.dto.CategoryRequest;
import com.subramanyav.ecoaiengine.dto.CategoryResponse;
import com.subramanyav.ecoaiengine.dto.*;
import com.subramanyav.ecoaiengine.service.*;
import com.subramanyav.ecoaiengine.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service){
        this.service = service;
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("GET METHOD HIT ✅");
        return "WORKING";
    }

    @PostMapping("/generate")
    public CategoryResponse generate(@RequestBody CategoryRequest request) {
        System.out.println("POST METHOD HIT 🔥");

        return service.generate(request);
    }

}
