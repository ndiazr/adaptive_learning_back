package com.adaptativelearning.security;

import static java.util.Collections.emptyList;

import com.adaptativelearning.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException
    {
        com.adaptativelearning.user.User user = userRepository.findByIdNumber(Integer
            .valueOf(username));

        if (user == null)
        {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getIdNumber().toString(), user.getPassword(), emptyList());
    }
}
