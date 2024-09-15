package com.web.sample.rest.controllers;

import com.web.sample.rest.error.FoodOutletNotFoundException;
import com.web.sample.rest.model.FoodOutlet;
import com.web.sample.rest.services.FoodOutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("foodoutlet")
public class FoodOutletController {

    private FoodOutletService foodOutletService;

    public FoodOutletController(FoodOutletService foodOutletService) {
        this.foodOutletService = foodOutletService;
    }

    @GetMapping(value = {"/list/{city}", "/list/{city}/{pageNo}"})
    public List<FoodOutlet> findAllFoodOutlets(@PathVariable String city, @PathVariable(required = false) Integer pageNo) throws FoodOutletNotFoundException {
        return this.foodOutletService.findAllFoodOutlets(city, ObjectUtils.isEmpty(pageNo)?0:pageNo);
    }

}
