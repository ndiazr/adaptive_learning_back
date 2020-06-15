package com.adaptativelearning.category;

import com.adaptativelearning.base.BaseRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Integer>
{
    List<Category> findByIdArea(Integer idArea);
}
