package com.adaptativelearning.repository;

import com.adaptativelearning.entities.Grade;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends BaseRepository<Grade, Integer>
{
}
