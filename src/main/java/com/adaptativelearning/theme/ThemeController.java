package com.adaptativelearning.theme;

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
@RequestMapping("/themes")
public class ThemeController extends BaseController<Theme, Integer, Theme, Theme>
{
    @Autowired
    private ThemeService themeService;

    public ThemeController()
    {
        super(Theme.class, Theme.class, Theme.class);
    }

    @ApiOperation(value = "Find a resource by dba")
    @ApiResponse(code = 404, message = "The resource was not found")
    @GetMapping("/findByDba/{dba}")
    public ResponseEntity<List<Theme>> findResourceByTeacher(@PathVariable(name = "dba") Integer idDba)
    {
        return ResponseEntity.ok(themeService.findByDba(idDba));
    }
}
