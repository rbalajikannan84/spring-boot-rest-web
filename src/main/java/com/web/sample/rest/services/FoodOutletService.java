package com.web.sample.rest.services;

import com.web.sample.rest.error.FoodOutletNotFoundException;
import com.web.sample.rest.model.FoodOutlet;

import java.util.List;


public interface FoodOutletService {

    public List<FoodOutlet> findAllFoodOutlets(String city, int pageNo) throws FoodOutletNotFoundException;
}
