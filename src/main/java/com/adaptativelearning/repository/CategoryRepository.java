package com.adaptativelearning.repository;

import com.adaptativelearning.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Integer>
{
}
