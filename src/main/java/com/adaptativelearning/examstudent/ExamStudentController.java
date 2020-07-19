package com.adaptativelearning.examstudent;

import com.adaptativelearning.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam-students")
public class ExamStudentController
    extends BaseController<ExamStudent, Integer, ExamStudent, ExamStudent>
{
    public ExamStudentController()
    {
        super(ExamStudent.class, ExamStudent.class, ExamStudent.class);
    }
}
