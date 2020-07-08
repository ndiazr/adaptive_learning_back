package com.adaptativelearning.studentassignment;

import com.adaptativelearning.base.BaseController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student-assignment")
public class StudentAssignmentController
    extends BaseController<StudentAssignment, Integer, StudentAssignment, StudentAssignment>
{
    @Autowired
    private StudentAssignmentService studentAssignmentService;

    public StudentAssignmentController()
    {
        super(StudentAssignment.class, StudentAssignment.class, StudentAssignment.class);
    }

    @ApiOperation(value = "Find a resource by teacher")
    @ApiResponse(code = 404, message = "The resource was not found")
    @GetMapping("/findByTeacher/{teacher}")
    public ResponseEntity<List<StudentAssignment>> findResourceByTeacher(@PathVariable(name = "teacher") Integer idTeacher)
    {
        return ResponseEntity.ok(studentAssignmentService.findByTeacher(idTeacher));
    }

    @ApiOperation(value = "Find a resource by filters")
    @ApiResponse(code = 404, message = "The resource was not found")
    @GetMapping("/findByFilters")
    public ResponseEntity<List<StudentAssignment>> findResourceByFilters(@RequestParam(required = false, name = "teacher") Integer idTeacher,
        @RequestParam(required = false, name = "student") Integer idStudent,
        @RequestParam(required = false, name = "grade") Integer idGrade,
        @RequestParam(required = false, name = "area") Integer idArea)
    {
        return ResponseEntity.ok(studentAssignmentService.getAll());
    }
}