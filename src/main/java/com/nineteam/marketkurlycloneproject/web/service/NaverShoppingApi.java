package com.nineteam.marketkurlycloneproject.web.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nineteam.marketkurlycloneproject.web.dto.ProductResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Component
public class NaverShoppingApi {

    public List<ProductResponseDto> getShoppingData(String query) throws IOException {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "hSXk8OZHc4Q4kVZM_SNc");
        headers.add("X-Naver-Client-Secret", "pE1iuWO5kG");
        String body = "";
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity = rest.exchange(
                "https://openapi.naver.com/v1/search/shop.json?sim=date&query=" + query, HttpMethod.GET
                , requestEntity
                , String.class
        );

        String naverApiResponseJson = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode itemsNode = objectMapper.readTree(naverApiResponseJson).get("items");

        return objectMapper
                .readerFor(new TypeReference<List<ProductResponseDto>>() {})
                .readValue(itemsNode);
    }
}
