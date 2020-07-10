package com.adaptativelearning.exam;

import com.adaptativelearning.base.BaseRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends BaseRepository<Exam, Integer>
{
    List<Exam> findByIdTeacher(Integer idTeacher);
}
