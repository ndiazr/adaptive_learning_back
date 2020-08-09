package com.adaptativelearning.question;

import com.adaptativelearning.base.BaseRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends BaseRepository<Question, Integer>
{
    List<Question> findByIdTheme(Integer idTheme);
}
