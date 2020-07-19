package com.adaptativelearning.reinforcement;

import com.adaptativelearning.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reinforcements")
public class ReinforcementController
    extends BaseController<Reinforcement, Integer, Reinforcement, Reinforcement>
{
    public ReinforcementController()
    {
        super(Reinforcement.class, Reinforcement.class, Reinforcement.class);
    }
}
