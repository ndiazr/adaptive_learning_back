package com.adaptativelearning.examstudentreinforcement;

import com.adaptativelearning.base.BaseRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamStudentReinforcementRepository
    extends BaseRepository<ExamStudentReinforcement, Integer>
{
    List<ExamStudentReinforcement> findByIdExamStudent(Integer idExamStudent);
}
