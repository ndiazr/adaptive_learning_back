package com.adaptativelearning.city;

import com.adaptativelearning.base.BaseEntity;
import com.adaptativelearning.base.entityinfo.annotations.DropDown;
import com.adaptativelearning.base.entityinfo.annotations.LineText;
import com.adaptativelearning.department.Department;
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
@Table(name = "cities")
@Data
@EqualsAndHashCode(callSuper = false)
public class City extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @LineText(hidden = true, editable = false)
    private Integer id;

    @Column(nullable = false, length = 100)
    @LineText
    private String name;

    @OneToOne
    @JoinColumn(name = "id_department", insertable = false, updatable = false)
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private Department department;

    @Column(name = "id_department", nullable = false)
    @DropDown(query = "SELECT ID AS VALUE, NAME AS LABEL FROM DEPARTMENTS")
    private Integer idDepartment;
}