package com.adaptativelearning.examstudent;

import com.adaptativelearning.difficulty.Difficulty;
import com.adaptativelearning.mediacontent.MediaContent;
import com.adaptativelearning.theme.Theme;

public class QuestionDTO
{
    private Integer id;
    private String description;
    private Theme theme;
    private Integer idTheme;
    private Difficulty difficulty;
    private Integer idDifficulty;
    private MediaContent mediaContent;
    private Integer idContent;
}
