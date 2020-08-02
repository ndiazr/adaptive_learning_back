package com.adaptativelearning.reinforcement;

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
@RequestMapping("/reinforcements")
public class ReinforcementController
    extends BaseController<Reinforcement, Integer, Reinforcement, Reinforcement>
{
    @Autowired
    private ReinforcementService reinforcementService;

    public ReinforcementController()
    {
        super(Reinforcement.class, Reinforcement.class, Reinforcement.class);
    }

    @ApiOperation(value = "Find a resource by theme")
    @ApiResponse(code = 404, message = "The resource was not found")
    @GetMapping("/findByTheme/{theme}")
    public ResponseEntity<List<Reinforcement>> findResourceByTheme(@PathVariable(name = "theme") Integer idTheme)
    {
        return ResponseEntity.ok(reinforcementService.findByIdTheme(idTheme));
    }
}
