package com.adaptativelearning.controller;

import com.adaptativelearning.entities.Department;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController extends BaseController<Department, Integer, Department, Department>
{
    public DepartmentController()
    {
        super(Department.class, Department.class, Department.class);
    }
}
