package com.adaptativelearning.controller;

import com.adaptativelearning.entities.School;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schools")
public class SchoolController extends BaseController<School, Integer, School, School>
{
    public SchoolController()
    {
        super(School.class, School.class, School.class);
    }
}
