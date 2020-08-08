package com.adaptativelearning.examstudent;

import com.adaptativelearning.base.BaseEntity;
import com.adaptativelearning.exam.Exam;
import com.adaptativelearning.user.User;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "exam_student")
@Data
@EqualsAndHashCode(callSuper = false)
public class ExamStudent extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_exam", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Exam exam;

    @Column(name = "id_exam", nullable = false)
    private Integer idExam;

    @OneToOne
    @JoinColumn(name = "id_student", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User student;

    @Column(name = "id_student", nullable = false)
    private Integer idStudent;

    @Column(nullable = false, length = 100)
    private String state;

    @Temporal(TemporalType.DATE)
    @Column(name = "assignment_date")
    private Date assignmentDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "realization_date")
    private Date realizationDate;

    @Column(length = 100)
    private String questions;

    @Column(length = 100)
    private String answers;

    @Column(length = 100)
    private String reinforcements;

    private Integer result;

    @Column(name = "try_number", nullable = false)
    private Integer tryNumber;
}