package com.adaptativelearning.examstudent;

import com.adaptativelearning.base.BaseRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamStudentRepository extends BaseRepository<ExamStudent, Integer>
{
    @Query(value = "SELECT * FROM exam_student" + " WHERE" + " id_exam = :idExam"
        + " AND id_student = :idStudent", nativeQuery = true)
    List<ExamStudent> searchAssignment(@Param("idExam") Integer idExam,
        @Param("idStudent") Integer idStudent);

    List<ExamStudent> findByIdStudent(Integer idStudent);

    List<ExamStudent> findByIdExam(Integer idExam);
}
