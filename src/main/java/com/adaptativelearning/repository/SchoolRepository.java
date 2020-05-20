package com.adaptativelearning.repository;

import com.adaptativelearning.entities.School;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends BaseRepository<School, Integer>
{
}
