package com.kubel.service.dto;

import com.kubel.valid.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches
public class PasswordDto {
    @NotNull
    @NotEmpty
    private String newPassword;
    @NotNull
    @NotEmpty
    private String oldPassword;
    @NotNull
    @NotEmpty
    private String matchingNewPassword;
    @NotNull
    @NotEmpty
    private String token;
}
