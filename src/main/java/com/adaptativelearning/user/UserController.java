package com.adaptativelearning.user;

import com.adaptativelearning.base.BaseController;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, Integer, User, User>
{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
}
