package com.adaptativelearning.mediacontent;

import com.adaptativelearning.base.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "media_content")
@Data
@EqualsAndHashCode(callSuper = false)
public class MediaContent extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String mime;

    @Column(name = "ref_content", nullable = false, length = 300)
    private String refContent;

    @Column(name = "type_content", nullable = false, length = 100)
    private String typeContent;
}