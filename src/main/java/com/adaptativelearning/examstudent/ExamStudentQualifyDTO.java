package com.adaptativelearning.examstudent;

import java.util.List;
import lombok.Data;

@Data
public class ExamStudentQualifyDTO
{
    private Integer id;
    private List<Integer> answers;
}
