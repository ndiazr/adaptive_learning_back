package com.adaptativelearning.authentication;

import java.util.List;
import lombok.Data;

@Data
public class LoginResponse
{
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String username;
    private String email;
    private List<String> roles;

    public LoginResponse(String accessToken, Integer id, String username, String email, List<String> roles)
    {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
