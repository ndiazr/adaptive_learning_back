package com.adaptativelearning.examstudent;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExamStudentRetryDTO
{
    @NotNull
    private Integer exam;
    @NotNull
    private Integer student;
}
