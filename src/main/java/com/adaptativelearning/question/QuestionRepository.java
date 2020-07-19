package com.adaptativelearning.question;

import com.adaptativelearning.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends BaseRepository<Question, Integer>
{
}
