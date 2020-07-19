package com.adaptativelearning.answer;

import com.adaptativelearning.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answers")
public class AnswerController extends BaseController<Answer, Integer, Answer, Answer>
{
    public AnswerController()
    {
        super(Answer.class, Answer.class, Answer.class);
    }
}
