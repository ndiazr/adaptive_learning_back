package com.adaptativelearning.category;

import com.adaptativelearning.area.Area;
import com.adaptativelearning.base.BaseEntity;
import com.adaptativelearning.base.entityinfo.annotations.DropDown;
import com.adaptativelearning.base.entityinfo.annotations.LineText;
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
@Table(name = "categories")
@Data
@EqualsAndHashCode(callSuper = false)
public class Category extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @LineText(hidden = true, editable = false)
    private Integer id;

    @Column(length = 200)
    @LineText
    private String description;

    @Column(nullable = false, length = 100)
    @LineText
    private String name;

    @OneToOne
    @JoinColumn(name = "id_area", insertable = false, updatable = false)
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private Area area;

    @Column(name = "id_area", nullable = false)
    @DropDown(query = "SELECT areas.id AS value,"
        + " CONCAT(areas.name, ' - ', grade.name ) AS label"
        + ""
        + " FROM areas, grade WHERE areas.id_grade = grade.id")
    private Integer idArea;
}