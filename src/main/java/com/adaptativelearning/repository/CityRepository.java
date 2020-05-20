package com.adaptativelearning.repository;

import com.adaptativelearning.entities.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends BaseRepository<City, Integer>
{
}
