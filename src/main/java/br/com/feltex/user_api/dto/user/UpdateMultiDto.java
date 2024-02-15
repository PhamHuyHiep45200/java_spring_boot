package br.com.feltex.user_api.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class UpdateMultiDto {

    private List<Long> ids;
    private String username;
}
