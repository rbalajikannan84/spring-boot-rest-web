package com.web.sample.rest.utils;

import com.web.sample.rest.model.FoodOutlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class FoodOutletClient {

    @Autowired
    public RestTemplate restTemplate;

    public FoodOutletClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Page<FoodOutlets> getFoodOutlets() {
        String url = "https://jsonmock.hackerrank.com/api/food_outlets";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);

        ResponseEntity<PageImpl<FoodOutlets>> responseEntity = restTemplate.exchange(uriBuilder.toUriString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<PageImpl<FoodOutlets>>() {
                });

        return responseEntity.getBody();
    }

}
