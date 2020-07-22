package com.adaptativelearning.examstudent;

import com.adaptativelearning.question.Question;
import java.util.List;
import lombok.Data;

@Data
public class ExamStudentTakeDTO
{
    private Integer id;
    private Integer idExam;
    private Integer idStudent;
    private String state;
    private List<Question> questions;
    private Integer tryNumber;
}
