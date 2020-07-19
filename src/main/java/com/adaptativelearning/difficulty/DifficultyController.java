package com.adaptativelearning.difficulty;

import com.adaptativelearning.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/difficulties")
public class DifficultyController extends BaseController<Difficulty, Integer, Difficulty, Difficulty>
{
    public DifficultyController()
    {
        super(Difficulty.class, Difficulty.class, Difficulty.class);
    }
}
