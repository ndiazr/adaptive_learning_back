package com.adaptativelearning.area;

import com.adaptativelearning.base.BaseRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends BaseRepository<Area, Integer>
{
    List<Area> findByIdGrade(Integer idGrade);
}
