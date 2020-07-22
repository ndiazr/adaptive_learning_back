package com.adaptativelearning.question;

import com.adaptativelearning.answer.Answer;
import com.adaptativelearning.base.BaseEntity;
import com.adaptativelearning.difficulty.Difficulty;
import com.adaptativelearning.mediacontent.MediaContent;
import com.adaptativelearning.theme.Theme;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "questions")
@Data
@EqualsAndHashCode(callSuper = false)
public class Question extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    private String description;

    @OneToOne
    @JoinColumn(name = "id_theme", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Theme theme;

    @Column(name = "id_theme", nullable = false)
    private Integer idTheme;

    @OneToOne
    @JoinColumn(name = "id_difficulty", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Difficulty difficulty;

    @Column(name = "id_difficulty", nullable = false)
    private Integer idDifficulty;

    @OneToOne
    @JoinColumn(name = "id_content", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MediaContent mediaContent;

    @Column(name = "id_content", nullable = false)
    private Integer idContent;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Answer> answers;
}