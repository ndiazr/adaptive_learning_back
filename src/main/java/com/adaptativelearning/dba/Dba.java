package com.adaptativelearning.dba;

import com.adaptativelearning.base.BaseEntity;
import com.adaptativelearning.base.entityinfo.annotations.DropDown;
import com.adaptativelearning.base.entityinfo.annotations.LineText;
import com.adaptativelearning.category.Category;
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
@Table(name = "dbas")
@Data
@EqualsAndHashCode(callSuper = false)
public class Dba extends BaseEntity
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
    @JoinColumn(name = "id_category", insertable = false, updatable = false)
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private Category category;

    @Column(name = "id_category", nullable = false)
    @DropDown(query = "SELECT ID AS VALUE, NAME AS LABEL FROM CATEGORIES")
    private Integer idCategory;
}