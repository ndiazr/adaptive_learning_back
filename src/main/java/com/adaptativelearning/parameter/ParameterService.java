package com.adaptativelearning.parameter;

import com.adaptativelearning.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParameterService extends BaseService<Parameter, Integer>
{
    @Autowired
    private ParameterRepository parameterRepository;

    public Parameter findByParameterKey(String key)
    {
        return parameterRepository.findByParameterKey(key).orElse(null);
    }
}
