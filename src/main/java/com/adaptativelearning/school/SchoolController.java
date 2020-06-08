package com.adaptativelearning.school;

import com.adaptativelearning.base.BaseController;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schools")
public class SchoolController extends BaseController<School, Integer, School, School>
{
    public SchoolController()
    {
        super(School.class, School.class, School.class);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<School>> getAll()
    {
        return super.getAllResources();
    }
}
