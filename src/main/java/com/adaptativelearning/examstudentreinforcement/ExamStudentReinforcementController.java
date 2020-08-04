package com.adaptativelearning.examstudentreinforcement;

import com.adaptativelearning.base.BaseController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam-student-reinforcement")
public class ExamStudentReinforcementController
    extends BaseController<ExamStudentReinforcement, Integer, ExamStudentReinforcement, ExamStudentReinforcement>
{
    @Autowired
    private ExamStudentReinforcementService examStudentReinforcementService;

    public ExamStudentReinforcementController()
    {
        super(
            ExamStudentReinforcement.class,
            ExamStudentReinforcement.class,
            ExamStudentReinforcement.class);
    }

    @ApiOperation(value = "Find resources by exam student")
    @ApiResponse(code = 404, message = "The resource was not found")
    @GetMapping("/findByExamStudent/{examStudent}")
    public ResponseEntity<List<ExamStudentReinforcement>> findResourceByExamStudent(@PathVariable(name = "examStudent") Integer idExamStudent)
    {
        return ResponseEntity.ok(examStudentReinforcementService.findByExamStudent(idExamStudent));
    }
}
