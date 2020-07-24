package com.adaptativelearning.examstudent;

import com.adaptativelearning.base.BaseController;
import com.adaptativelearning.studentassignment.StudentAssignment;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/exam-students")
public class ExamStudentController
    extends BaseController<ExamStudent, Integer, ExamStudent, ExamStudent>
{
    @Autowired
    private ExamStudentService examStudentService;

    public ExamStudentController()
    {
        super(ExamStudent.class, ExamStudent.class, ExamStudent.class);
    }

    @GetMapping("/findByStudent/{student}")
    public ResponseEntity<List<ExamStudent>> findResourceByStudent(@PathVariable(name = "student") Integer idStudent)
    {
        return ResponseEntity.ok(examStudentService.findByStudent(idStudent));
    }

    @PostMapping("/assign")
    public ResponseEntity assignStudentsExam(@Valid @RequestBody ExamStudentAssignDTO examStudentAssignDTO)
    {
        try
        {
            examStudentService.assignStudentsExam(examStudentAssignDTO);
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/take-exam/{examStudent}")
    public ResponseEntity takeStudentExam(@PathVariable(name = "examStudent") Integer examStudent)
    {
        try
        {
            return ResponseEntity.ok(examStudentService.takeStudentExam(examStudent));
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                e.getStackTrace().toString());
        }
    }
}
