package com.adaptativelearning.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "schools")
@Data
public class School extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(length = 200)
    private String address;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(name = "phone_number", length = 100)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "id_city", insertable = false, updatable = false)
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private City city;

    @Column(name = "id_city", nullable = false)
    private Integer idCity;
}