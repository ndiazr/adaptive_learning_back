package com.adaptativelearning.dba;

import com.adaptativelearning.base.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbaService extends BaseService<Dba, Integer>
{
    @Autowired
    private DbaRepository dbaRepository;

    public List<Dba> findByCategory(Integer idCategory)
    {
        return dbaRepository.findByIdCategory(idCategory);
    }
}
