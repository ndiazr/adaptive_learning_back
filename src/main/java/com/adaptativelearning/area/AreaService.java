package com.adaptativelearning.area;

import com.adaptativelearning.base.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaService extends BaseService<Area, Integer>
{
    @Autowired
    private AreaRepository areaRepository;

    public List<Area> findByGrade(Integer idGrade)
    {
        return areaRepository.findByIdGrade(idGrade);
    }
}
