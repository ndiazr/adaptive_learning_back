package com.adaptativelearning.authentication;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpRequest
{
    @NotNull
    private Integer idNumber;

    @NotBlank
    private String names;

    @NotBlank
    private String lastNames;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String address;

    private String phoneNumber;

    @NotNull
    private Integer idRole;
}
