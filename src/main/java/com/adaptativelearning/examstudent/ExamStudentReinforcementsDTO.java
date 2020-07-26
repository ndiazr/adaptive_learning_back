package com.adaptativelearning.examstudent;

import com.adaptativelearning.area.Area;
import com.adaptativelearning.reinforcement.Reinforcement;
import java.util.List;
import lombok.Data;

@Data
public class ExamStudentReinforcementsDTO
{
    private Integer id;
    private Integer idExam;
    private Integer idStudent;
    private Area area;
    private List<Reinforcement> reinforcements;
}
