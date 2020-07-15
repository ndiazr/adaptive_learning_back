package com.adaptativelearning.exam;

import com.adaptativelearning.area.Area;
import com.adaptativelearning.base.BaseEntity;
import com.adaptativelearning.base.entityinfo.annotations.DropDown;
import com.adaptativelearning.base.entityinfo.annotations.LineText;
import com.adaptativelearning.category.Category;
import com.adaptativelearning.dba.Dba;
import com.adaptativelearning.grade.Grade;
import com.adaptativelearning.theme.Theme;
import com.adaptativelearning.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "exams")
@Data
@EqualsAndHashCode(callSuper = false)
public class Exam extends BaseEntity
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
    @LineText
    private User teacher;

    @Column(name = "id_teacher", nullable = false)
    private int idTeacher;

    @Column(nullable = false, length = 100)
    @LineText
    private String name;

    @Column(length = 300)
    @LineText
    private String description;

    @OneToOne
    @JoinColumn(name = "id_grade", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private Grade grade;

    @Column(name = "id_grade", nullable = false)
    @DropDown(query = "SELECT ID AS value, NAME AS label FROM grade")
    private int idGrade;

    @OneToOne
    @JoinColumn(name = "id_area", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private Area area;

    @Column(name = "id_area", nullable = false)
    @DropDown(query = "SELECT id AS value, name AS label FROM areas")
    private int idArea;

    @OneToOne
    @JoinColumn(name = "id_category", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private Category category;

    @Column(name = "id_category", nullable = false)
    @DropDown(query = "SELECT ID AS value, NAME AS label FROM categories")
    private int idCategory;

    @OneToOne
    @JoinColumn(name = "id_dba", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private Dba dba;

    @Column(name = "id_dba", nullable = false)
    @DropDown(query = "SELECT ID AS value, NAME AS label FROM dbas")
    private int idDba;

    @OneToOne
    @JoinColumn(name = "id_theme", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private Theme theme;

    @Column(name = "id_theme", nullable = false)
    @DropDown(query = "SELECT ID AS value, NAME AS label FROM themes")
    private int idTheme;

    @Column(name = "is_private", nullable = false)
    private Short isPrivate;
}