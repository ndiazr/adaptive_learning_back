package com.adaptativelearning.studentassignment;

import com.adaptativelearning.area.Area;
import com.adaptativelearning.base.BaseEntity;
import com.adaptativelearning.base.entityinfo.annotations.DropDown;
import com.adaptativelearning.base.entityinfo.annotations.LineText;
import com.adaptativelearning.grade.Grade;
import com.adaptativelearning.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "student_assignment", uniqueConstraints = @UniqueConstraint(columnNames = {
    "id_student", "id_grade", "id_area"}))
@Data
@EqualsAndHashCode(callSuper = false)
public class StudentAssignment extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @LineText(hidden = true, editable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_teacher", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User teacher;

    @Column(name = "id_teacher", nullable = false)
    private Integer idTeacher;

    @OneToOne
    @JoinColumn(name = "id_student", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User student;

    @Column(name = "id_student", nullable = false)
    private Integer idStudent;

    @OneToOne
    @JoinColumn(name = "id_grade", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private Grade grade;

    @Column(name = "id_grade", nullable = false)
    @DropDown(query = "SELECT ID AS VALUE, NAME AS LABEL FROM GRADE")
    private Integer idGrade;

    @OneToOne
    @JoinColumn(name = "id_area", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private Area area;

    @Column(name = "id_area", nullable = false)
    @DropDown(query = "SELECT ID AS VALUE, NAME AS LABEL FROM AREAS")
    private Integer idArea;
}