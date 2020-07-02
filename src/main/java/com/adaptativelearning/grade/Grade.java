package com.adaptativelearning.grade;

import com.adaptativelearning.base.BaseEntity;
import com.adaptativelearning.base.entityinfo.annotations.LineText;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "grade")
@Data
@EqualsAndHashCode(callSuper = false)
public class Grade extends BaseEntity
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
}