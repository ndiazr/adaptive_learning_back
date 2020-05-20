package com.adaptativelearning.user;

import com.adaptativelearning.base.BaseEntity;
import com.adaptativelearning.role.Role;
import com.adaptativelearning.school.School;
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
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(length = 200)
    private String address;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "id_number", nullable = false)
    private Integer idNumber;

    @Column(name = "last_names", nullable = false, length = 100)
    private String lastNames;

    @Column(nullable = false, length = 100)
    private String names;

    @Column(nullable = false, length = 61)
    private String password;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "id_role", insertable = false, updatable = false)
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Role role;

    @Column(name = "id_role", nullable = false)
    private Integer idRole;

    @OneToOne
    @JoinColumn(name = "id_school", insertable = false, updatable = false)
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private School school;

    @Column(name = "id_role")
    private Integer idSchool;
}