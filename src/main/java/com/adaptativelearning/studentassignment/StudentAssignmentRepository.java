package com.adaptativelearning.studentassignment;

import com.adaptativelearning.base.BaseRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAssignmentRepository extends BaseRepository<StudentAssignment, Integer>
{
    List<StudentAssignment> findByIdTeacher(Integer idTeacher);
}
