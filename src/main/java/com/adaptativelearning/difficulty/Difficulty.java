package com.adaptativelearning.difficulty;

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
@Table(name = "difficulties")
@Data
@EqualsAndHashCode(callSuper = false)
public class Difficulty extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
	@LineText(hidden = true, editable = false, hiddenInTable = true)
    private Integer id;

    @Column(nullable = false, length = 50)
	@LineText
    private String name;
}