package com.adaptativelearning.exam;

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
@RequestMapping("/exams")
public class ExamController extends BaseController<Exam, Integer, Exam, Exam>
{
    @Autowired
    private ExamService examService;

    public ExamController()
    {
        super(Exam.class, Exam.class, Exam.class);
    }

    @ApiOperation(value = "Find a resource by teacher")
    @ApiResponse(code = 404, message = "The resource was not found")
    @GetMapping("/findByTeacher/{teacher}")
    public ResponseEntity<List<Exam>> findResourceByTeacher(@PathVariable(name = "teacher") Integer idTeacher)
    {
        return ResponseEntity.ok(examService.findByTeacher(idTeacher));
    }
}
