package com.web.sample.rest.services.impl;

import com.web.sample.rest.error.FoodOutletNotFoundException;
import com.web.sample.rest.model.FoodOutlet;
import com.web.sample.rest.model.FoodOutlets;
import com.web.sample.rest.services.FoodOutletService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FoodOutletServiceImpl implements FoodOutletService {

    private final RestTemplate restTemplate;

    public FoodOutletServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<FoodOutlet> findAllFoodOutlets(String city, int pageNo) throws FoodOutletNotFoundException
    {
        List<FoodOutlet> allData = new ArrayList<FoodOutlet>();
        FoodOutlets foodOutlets = restTemplate.getForObject("https://jsonmock.hackerrank.com/api/food_outlets?city={city}", FoodOutlets.class ,city);
        if(ObjectUtils.isEmpty(foodOutlets) || foodOutlets.getTotalPages() == 0) {
            throw new FoodOutletNotFoundException("No food Outlets found for the city: " + city + "." +
                    " Outlet information of Seattle, Houston, Miami are available.");
        }else if(pageNo > 0 && pageNo > foodOutlets.getTotalPages()) {
            throw new FoodOutletNotFoundException("Page "+pageNo+" requested not found for the city: " + city + ".");
        }
        if(pageNo > 0) {
            foodOutlets = restTemplate.getForObject("https://jsonmock.hackerrank.com/api/food_outlets?city={city}&page={page}", FoodOutlets.class, city, pageNo);
            allData.addAll(Objects.requireNonNull(foodOutlets).getData());
        }else {
            for (int i = foodOutlets.getPage(); i <= foodOutlets.getTotalPages(); i++) {
                foodOutlets = restTemplate.getForObject("https://jsonmock.hackerrank.com/api/food_outlets?city={city}&page={page}", FoodOutlets.class, city, String.valueOf(i));
                allData.addAll(Objects.requireNonNull(foodOutlets).getData());
            }
        }
        return allData;
    }
}
