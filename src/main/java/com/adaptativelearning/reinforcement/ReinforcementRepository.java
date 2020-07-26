package com.adaptativelearning.reinforcement;

import com.adaptativelearning.base.BaseRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ReinforcementRepository extends BaseRepository<Reinforcement, Integer>
{
    List<Reinforcement> findByIdTheme(Integer idTheme);
}
