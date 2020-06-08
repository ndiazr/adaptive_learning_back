package com.adaptativelearning.security.services;

import com.adaptativelearning.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
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
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return UserDetailsImpl.build(user);
    }
}
