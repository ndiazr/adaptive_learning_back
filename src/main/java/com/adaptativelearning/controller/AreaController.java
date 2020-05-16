package com.adaptativelearning.controller;

import com.adaptativelearning.entities.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/areas")
public class AreaController extends BaseController<Area, Integer, Area, Area>
{
    public AreaController()
    {
        super(Area.class, Area.class, Area.class);
    }
}
