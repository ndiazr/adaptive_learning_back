package com.adaptativelearning.reinforcement;

import com.adaptativelearning.base.BaseEntity;
import com.adaptativelearning.base.entityinfo.annotations.DropDown;
import com.adaptativelearning.base.entityinfo.annotations.LineText;
import com.adaptativelearning.difficulty.Difficulty;
import com.adaptativelearning.theme.Theme;
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
@Table(name = "reinforcement")
@Data
@EqualsAndHashCode(callSuper = false)
public class Reinforcement extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @LineText(hidden = true, editable = false)
    private Integer id;

    @Column(nullable = false, length = 100)
    @LineText
    private String title;

    @Column(length = 250)
    @LineText
    private String description;

    @OneToOne
    @JoinColumn(name = "id_theme", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private Theme theme;

    @Column(name = "id_theme", nullable = false)
    @DropDown(query = "SELECT ID AS value, NAME AS label FROM themes")
    private Integer idTheme;

    @OneToOne
    @JoinColumn(name = "id_difficulty", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @LineText
    private Difficulty difficulty;

    @Column(name = "id_difficulty", nullable = false)
    @DropDown(query = "SELECT ID AS value, NAME AS label FROM difficulties")
    private Integer idDifficulty;

    @Column(nullable = false, length = 300)
    @LineText
    private String link;
}