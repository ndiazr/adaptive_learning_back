package com.adaptativelearning.school;

import com.adaptativelearning.base.BaseEntity;
import com.adaptativelearning.base.entityinfo.annotations.DropDown;
import com.adaptativelearning.base.entityinfo.annotations.LineText;
import com.adaptativelearning.city.City;
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
@Table(name = "schools")
@Data
@EqualsAndHashCode(callSuper = false)
public class School extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @LineText(hidden = true, editable = false)
    private Integer id;

    @Column(nullable = false, length = 200)
    @LineText
    private String name;

    @Column(name = "phone_number", length = 100)
    @LineText
    private String phoneNumber;

    @Column(length = 200)
    @LineText
    private String address;

    @OneToOne
    @JoinColumn(name = "id_city", insertable = false, updatable = false)
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private City city;

    @Column(name = "id_city", nullable = false)
    @DropDown(query = "SELECT ID AS value, NAME AS label FROM cities")
    private Integer idCity;
}