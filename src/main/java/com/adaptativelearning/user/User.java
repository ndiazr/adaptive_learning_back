package com.adaptativelearning.user;

import com.adaptativelearning.base.BaseEntity;
import com.adaptativelearning.base.entityinfo.annotations.DropDown;
import com.adaptativelearning.base.entityinfo.annotations.LineText;
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
    @LineText(hidden = true, editable = false, hiddenInTable = true)
    private Integer id;

    @Column(name = "id_number", nullable = false)
    @LineText
    private Integer idNumber;

    @Column(nullable = false, length = 100)
    @LineText
    private String names;

    @Column(name = "last_names", nullable = false, length = 100)
    @LineText
    private String lastNames;

    @Column(name = "phone_number", length = 20)
    @LineText
    private String phoneNumber;

    @Column(length = 200)
    @LineText
    private String address;

    @Column(nullable = false, length = 100)
    @LineText
    private String email;

    @Column(nullable = false, length = 61)
    @LineText(hiddenInTable = true)
    private String password;

    @OneToOne
    @JoinColumn(name = "id_role", insertable = false, updatable = false)
    @JsonBackReference(value = "role")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private Role role;

    @Column(name = "id_role", nullable = false)
    @DropDown(query = "SELECT ID AS value, NAME AS label FROM roles")
    private Integer idRole;

    @OneToOne
    @JoinColumn(name = "id_school", insertable = false, updatable = false)
    @JsonBackReference(value = "school")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private School school;

    @Column(name = "id_school")
    @DropDown(query = "SELECT ID AS value, NAME AS label FROM schools")
    private Integer idSchool;
}