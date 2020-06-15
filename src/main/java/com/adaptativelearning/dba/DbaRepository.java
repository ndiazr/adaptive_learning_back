package com.adaptativelearning.dba;

import com.adaptativelearning.base.BaseRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface DbaRepository extends BaseRepository<Dba, Integer>
{
    List<Dba> findByIdCategory(Integer idCategory);
}
