package com.adaptativelearning.base;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity, I extends Serializable> extends JpaRepository<T, I>
{
}
