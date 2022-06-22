package com.nineteam.marketkurlycloneproject.web.controller;

import com.nineteam.marketkurlycloneproject.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {

    private final ProductService service;

    @GetMapping("/main")
    public ResponseEntity<?>getMainProducts(){
        return new ResponseEntity<>(service.getMain(), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?>getProductDetail(@PathVariable Long id){
        return new ResponseEntity<>(service.getProductDetail(id), HttpStatus.OK);
    }

}
