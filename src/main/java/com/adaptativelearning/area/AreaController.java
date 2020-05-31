package com.adaptativelearning.area;

import com.adaptativelearning.area.Area;
import com.adaptativelearning.base.BaseController;
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
