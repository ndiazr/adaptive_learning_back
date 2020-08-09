package com.adaptativelearning.question;

import com.adaptativelearning.base.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService extends BaseService<Question, Integer>
{
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> findByIdTheme(Integer idTheme)
    {
        return questionRepository.findByIdTheme(idTheme);
    }
}
