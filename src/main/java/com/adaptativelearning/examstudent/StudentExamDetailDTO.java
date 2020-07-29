package com.adaptativelearning.examstudent;

import com.adaptativelearning.reinforcement.Reinforcement;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class StudentExamDetailDTO
{
    private String state;
    private Date realizationDate;
    private Integer result;
    private Integer tryNumber;
    private List<Reinforcement> reinforcements;
}
