package com.adaptativelearning.exam;

import com.adaptativelearning.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exams")
public class ExamController extends BaseController<Exam, Integer, Exam, Exam>
{
    public ExamController()
    {
        super(Exam.class, Exam.class, Exam.class);
    }
}
