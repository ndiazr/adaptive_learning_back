package com.adaptativelearning.studentassignment;

import com.adaptativelearning.base.BaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentAssignmentService extends BaseService<StudentAssignment, Integer>
{
    @Autowired
    private StudentAssignmentRepository studentAssignmentRepository;

    public List<StudentAssignment> findByTeacher(Integer idTeacher)
    {
        return studentAssignmentRepository.findByIdTeacher(idTeacher);
    }
}
