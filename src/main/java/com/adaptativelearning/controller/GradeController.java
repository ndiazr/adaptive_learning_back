package com.adaptativelearning.controller;

import com.adaptativelearning.base.BaseController;
import com.adaptativelearning.grade.Grade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grade")
public class GradeController extends BaseController<Grade, Integer, Grade, Grade>
{
    public GradeController()
    {
        super(Grade.class, Grade.class, Grade.class);
    }
}
