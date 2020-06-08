package com.adaptativelearning.user;

import com.adaptativelearning.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Integer>
{
    User findByIdNumber(Integer idNumber);

    Boolean existsByIdNumber(Integer idNumber);
}
