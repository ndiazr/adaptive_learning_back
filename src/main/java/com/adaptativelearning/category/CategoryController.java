package com.adaptativelearning.category;

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
@RequestMapping("/categories")
public class CategoryController extends BaseController<Category, Integer, Category, Category>
{
    @Autowired
    private CategoryService categoryService;

    public CategoryController()
    {
        super(Category.class, Category.class, Category.class);
    }

    @ApiOperation(value = "Find a resource by area")
    @ApiResponse(code = 404, message = "The resource was not found")
    @GetMapping("/findByArea/{area}")
    public ResponseEntity<List<Category>> findResourceByTeacher(@PathVariable(name = "area") Integer idArea)
    {
        return ResponseEntity.ok(categoryService.findByArea(idArea));
    }
}
