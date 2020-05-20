package com.adaptativelearning.controller;

import com.adaptativelearning.base.BaseController;
import com.adaptativelearning.parameter.Parameter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parameters")
public class ParameterController extends BaseController<Parameter, Integer, Parameter, Parameter>
{
    public ParameterController()
    {
        super(Parameter.class, Parameter.class, Parameter.class);
    }
}
