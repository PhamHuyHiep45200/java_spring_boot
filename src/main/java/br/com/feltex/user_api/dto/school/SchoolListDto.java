package br.com.feltex.user_api.dto.school;

import br.com.feltex.user_api.entity.UsersEntity;
import lombok.Data;

import java.util.List;

@Data
public class SchoolListDto extends SchoolDto {

    private Long id;

    private List<UsersEntity> users;
}
