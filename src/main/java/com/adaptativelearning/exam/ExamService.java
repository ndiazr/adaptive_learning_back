package com.adaptativelearning.exam;

import com.adaptativelearning.base.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService extends BaseService<Exam, Integer>
{
    @Autowired
    private ExamRepository examRepository;

    public List<Exam> findByTeacher(Integer idTeacher)
    {
        return examRepository.findByIdTeacher(idTeacher);
    }
}
