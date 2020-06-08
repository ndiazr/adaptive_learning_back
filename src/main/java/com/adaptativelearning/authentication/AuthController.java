package com.adaptativelearning.authentication;

import com.adaptativelearning.security.jwt.JwtUtils;
import com.adaptativelearning.security.services.UserDetailsImpl;
import com.adaptativelearning.user.User;
import com.adaptativelearning.user.UserRepository;
import com.adaptativelearning.user.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Setter
    protected ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
    {
        return ResponseEntity.ok(authenticate(loginRequest.getUsername(),
            loginRequest.getPassword()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest)
    {
        if (userRepository.existsByIdNumber(signUpRequest.getIdNumber()))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(
                "Error: Username is already used!"));
        }
        User user = userService.save(mapSignUpRequestToUser(signUpRequest));

        return ResponseEntity.ok(authenticate(String.valueOf(user.getIdNumber()),
            signUpRequest.getPassword()));
    }

    private LoginResponse authenticate(String username, String password)
    {
        Authentication authentication =
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles =
            userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                Collectors.toList());

        return new LoginResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles);
    }

    private User mapSignUpRequestToUser(SignUpRequest signUpRequest)
    {
        User user = new User();
        user.setIdNumber(signUpRequest.getIdNumber());
        user.setNames(signUpRequest.getNames());
        user.setLastNames(signUpRequest.getLastNames());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setIdRole(signUpRequest.getIdRole());

        if (signUpRequest.getAddress() != null)
        {
            user.setAddress(signUpRequest.getAddress());
        }

        if (signUpRequest.getPhoneNumber() != null)
        {
            user.setPhoneNumber(signUpRequest.getPhoneNumber());
        }

        if (signUpRequest.getIdSchool() != null)
        {
            user.setIdSchool(signUpRequest.getIdSchool());
        }
        return user;
    }
}
