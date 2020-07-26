package com.adaptativelearning.reinforcement;

import com.adaptativelearning.base.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReinforcementService extends BaseService<Reinforcement, Integer>
{
    @Autowired
    private ReinforcementRepository reinforcementRepository;

    public List<Reinforcement> findByIdTheme(Integer idTheme)
    {
        return reinforcementRepository.findByIdTheme(idTheme);
    }
}
