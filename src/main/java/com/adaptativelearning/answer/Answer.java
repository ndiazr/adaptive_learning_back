package com.adaptativelearning.answer;

import com.adaptativelearning.base.BaseEntity;
import com.adaptativelearning.mediacontent.MediaContent;
import com.adaptativelearning.question.Question;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "answers")
@Data
@EqualsAndHashCode(callSuper = false)
public class Answer extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(length = 300)
    private String description;

    @Column(name = "is_correct", nullable = false)
    private Short isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question", insertable = false, updatable = false)
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Question question;

    @Column(name = "id_question", nullable = false)
    private Integer idQuestion;

    @OneToOne
    @JoinColumn(name = "id_content", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MediaContent mediaContent;

    @Column(name = "id_content", nullable = false)
    private Integer idContent;
}