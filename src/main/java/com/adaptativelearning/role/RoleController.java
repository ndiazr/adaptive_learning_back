package com.adaptativelearning.role;

import com.adaptativelearning.base.BaseController;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/findAll")
    public ResponseEntity<List<Role>> getAll()
    {
        return super.getAllResources();
    }
}
