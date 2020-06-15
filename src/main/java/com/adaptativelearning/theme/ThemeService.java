package com.adaptativelearning.theme;

import com.adaptativelearning.base.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThemeService extends BaseService<Theme, Integer>
{
    @Autowired
    private ThemeRepository themeRepository;

    public List<Theme> findByDba(Integer idDba)
    {
        return themeRepository.findByIdDba(idDba);
    }
}
