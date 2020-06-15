package com.adaptativelearning.theme;

import com.adaptativelearning.base.BaseRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends BaseRepository<Theme, Integer>
{
    List<Theme> findByIdDba(Integer idDba);
}
