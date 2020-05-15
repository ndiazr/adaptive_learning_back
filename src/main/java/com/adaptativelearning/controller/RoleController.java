package com.adaptativelearning.controller;

import com.adaptativelearning.entities.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController<Role, Integer, Role, Role>
{
    public RoleController()
    {
        super(Role.class, Role.class, Role.class);
    }
}
