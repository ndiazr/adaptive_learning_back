package com.adaptativelearning.user;

import com.adaptativelearning.base.BaseController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, Integer, User, User>
{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    public UserController()
    {
        super(User.class, User.class, User.class);
    }

    @Override
    public ResponseEntity<User> createResource(@RequestBody @Valid User requestEntity)
    {
        requestEntity.setPassword(bCryptPasswordEncoder.encode(requestEntity.getPassword()));
        return super.createResource(requestEntity);
    }

    @ApiOperation(value = "Find a resource with student role by parameter")
    @ApiResponse(code = 404, message = "The resource was not found")
    @GetMapping("/searchStudentUser")
    public ResponseEntity<List<User>> searchStudentUser(@RequestParam String search)
    {
        return ResponseEntity.ok(userRepository.searchStudentUser(search));
    }
}
