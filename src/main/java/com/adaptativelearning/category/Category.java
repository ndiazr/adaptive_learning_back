package com.adaptativelearning.category;

import com.adaptativelearning.area.Area;
import com.adaptativelearning.base.BaseEntity;
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
    private Integer id;

    @Column(length = 200)
    private String description;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToOne
    @JoinColumn(name = "id_area", insertable = false, updatable = false)
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Area area;

    @Column(name = "id_area", nullable = false)
    private Integer idArea;
}