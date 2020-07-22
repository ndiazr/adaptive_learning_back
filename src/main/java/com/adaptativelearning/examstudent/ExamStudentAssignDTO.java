package com.adaptativelearning.examstudent;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExamStudentAssignDTO
{
    @NotNull
    private Integer exam;
    @NotNull
    private List<Integer> students;
}
