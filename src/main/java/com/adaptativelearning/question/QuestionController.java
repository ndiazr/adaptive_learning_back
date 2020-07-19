package com.adaptativelearning.question;

import com.adaptativelearning.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionController extends BaseController<Question, Integer, Question, Question>
{
    public QuestionController()
    {
        super(Question.class, Question.class, Question.class);
    }
}
