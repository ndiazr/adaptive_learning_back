package com.adaptativelearning.dba;

import com.adaptativelearning.base.BaseController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dbas")
public class DbaController extends BaseController<Dba, Integer, Dba, Dba>
{
    @Autowired
    private DbaService dbaService;

    public DbaController()
    {
        super(Dba.class, Dba.class, Dba.class);
    }

    @ApiOperation(value = "Find a resource by category")
    @ApiResponse(code = 404, message = "The resource was not found")
    @GetMapping("/findByCategory/{category}")
    public ResponseEntity<List<Dba>> findResourceByTeacher(@PathVariable(name = "category") Integer idCategory)
    {
        return ResponseEntity.ok(dbaService.findByCategory(idCategory));
    }
}
