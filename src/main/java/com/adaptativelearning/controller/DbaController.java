package com.adaptativelearning.controller;

import com.adaptativelearning.base.BaseController;
import com.adaptativelearning.dba.Dba;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dbas")
public class DbaController extends BaseController<Dba, Integer, Dba, Dba>
{
    public DbaController()
    {
        super(Dba.class, Dba.class, Dba.class);
    }
}
