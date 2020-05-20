package com.adaptativelearning.dba;

import com.adaptativelearning.base.BaseEntity;
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
    private Integer id;

    @Column(length = 200)
    private String description;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToOne
    @JoinColumn(name = "id_category", insertable = false, updatable = false)
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Category category;

    @Column(name = "id_category", nullable = false)
    private Integer idCategory;
}