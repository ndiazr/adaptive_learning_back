package com.adaptativelearning.user;

import static java.lang.String.format;

import com.adaptativelearning.base.BaseController;
import com.adaptativelearning.base.validators.NullValidatorBuilder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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

    @Override
    public ResponseEntity<User> updateResource(@PathVariable() Integer id,
        @RequestBody User requestEntity)
    {
        User entityToUpdate = userService.findById(id);

        NullValidatorBuilder.builder().httpStatus(HttpStatus.NOT_FOUND).message(format(NOT_FOUND_ERROR_FORMAT,
            id)).validate(entityToUpdate);

        if (!requestEntity.getPassword().equals(entityToUpdate.getPassword()))
        {
            requestEntity.setPassword(bCryptPasswordEncoder.encode(requestEntity.getPassword()));
        }
        return super.updateResource(id, requestEntity);
    }

    @ApiOperation(value = "Find a resource with student role by parameter")
    @ApiResponse(code = 404, message = "The resource was not found")
    @GetMapping("/searchStudentUser")
    public ResponseEntity<List<User>> searchStudentUser(@RequestParam String search)
    {
        return ResponseEntity.ok(userRepository.searchStudentUser(search));
    }
}
