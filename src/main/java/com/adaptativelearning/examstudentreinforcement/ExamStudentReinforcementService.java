package com.adaptativelearning.examstudentreinforcement;

import com.adaptativelearning.base.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamStudentReinforcementService extends BaseService<ExamStudentReinforcement, Integer>
{
    @Autowired
    private ExamStudentReinforcementRepository examStudentReinforcementRepository;

    public List<ExamStudentReinforcement> findByExamStudent(Integer idExamStudent)
    {
        return examStudentReinforcementRepository.findByIdExamStudent(idExamStudent);
    }
}
