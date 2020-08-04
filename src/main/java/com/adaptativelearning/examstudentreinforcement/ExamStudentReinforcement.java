package com.adaptativelearning.examstudentreinforcement;

import com.adaptativelearning.base.BaseEntity;
import com.adaptativelearning.examstudent.ExamStudent;
import com.adaptativelearning.reinforcement.Reinforcement;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "exam_student_reinforcement")
@Data
@EqualsAndHashCode(callSuper = false)
public class ExamStudentReinforcement extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_exam_student", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ExamStudent examStudent;

    @Column(name = "id_exam_student", nullable = false)
    private Integer idExamStudent;

    @OneToOne
    @JoinColumn(name = "id_reinforcement", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Reinforcement reinforcement;

    @Column(name = "id_reinforcement", nullable = false)
    private Integer idReinforcement;

    @Column(name = "is_read", nullable = false)
    private Short isRead;
}
