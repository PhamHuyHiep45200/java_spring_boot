package br.com.feltex.user_api.dto.user;

import lombok.Data;

@Data
public class UserDto {

    private String username;

    private String email;

    private Long school;
}
