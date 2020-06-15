package com.adaptativelearning.category;

import com.adaptativelearning.base.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category, Integer>
{
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findByArea(Integer idArea)
    {
        return categoryRepository.findByIdArea(idArea);
    }
}
