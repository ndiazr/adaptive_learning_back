package com.adaptativelearning.repository;

import com.adaptativelearning.entities.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends BaseRepository<Department, Integer>
{
}
