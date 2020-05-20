package com.adaptativelearning.controller;

import com.adaptativelearning.entities.City;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController extends BaseController<City, Integer, City, City>
{
    public CityController()
    {
        super(City.class, City.class, City.class);
    }
}
