package com.adaptativelearning.repository;

import com.adaptativelearning.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Integer>
{
    User findByIdNumber(Integer idNumber);
}
