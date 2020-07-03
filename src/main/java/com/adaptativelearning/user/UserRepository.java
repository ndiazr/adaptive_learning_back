package com.adaptativelearning.user;

import com.adaptativelearning.base.BaseRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Integer>
{
    User findByIdNumber(Integer idNumber);

    Boolean existsByIdNumber(Integer idNumber);

    @Query(
        value = "SELECT * FROM users u, roles r"
            + " WHERE (u.id_number like CONCAT('%', :search ,'%')"
            + " OR u.names like CONCAT('%', :search ,'%')"
            + " OR u.last_names like CONCAT('%', :search ,'%'))"
            + " AND u.id_role = r.id"
            + " AND r.name like '%estudiante%'",
        nativeQuery = true)
    List<User> searchStudentUser(@Param("search") String search);
}
