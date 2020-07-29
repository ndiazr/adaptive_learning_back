package com.adaptativelearning.examstudent;

import com.adaptativelearning.exam.Exam;
import com.adaptativelearning.user.User;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class ExamStudentForTeacherDTO
{
    private Integer idExam;
    private Exam exam;
    private Map<String, List<StudentExamDetailDTO>> students;
}
