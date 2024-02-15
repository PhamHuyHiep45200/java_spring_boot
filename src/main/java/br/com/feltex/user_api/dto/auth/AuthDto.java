package br.com.feltex.user_api.dto.auth;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class AuthDto {

    @NotNull
    @NotBlank
    private String email;
}
