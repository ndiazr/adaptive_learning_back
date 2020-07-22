package com.adaptativelearning.parameter;

import com.adaptativelearning.base.BaseRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends BaseRepository<Parameter, Integer>
{
    Optional<Parameter> findByParameterKey(String key);
}
