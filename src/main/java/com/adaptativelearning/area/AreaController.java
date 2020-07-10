package com.adaptativelearning.area;

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
@RequestMapping("/areas")
public class AreaController extends BaseController<Area, Integer, Area, Area>
{
    @Autowired
    private AreaService areaService;

    public AreaController()
    {
        super(Area.class, Area.class, Area.class);
    }

    @ApiOperation(value = "Find a resource by grade")
    @ApiResponse(code = 404, message = "The resource was not found")
    @GetMapping("/findByGrade/{grade}")
    public ResponseEntity<List<Area>> findResourceByGrade(@PathVariable(name = "grade") Integer idGrade)
    {
        return ResponseEntity.ok(areaService.findByGrade(idGrade));
    }
}
