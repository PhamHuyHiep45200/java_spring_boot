package br.com.feltex.user_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "schools")
public class SchoolsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schoolId;

    @NotNull
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "school")
    @JsonIgnore
    private List<UsersEntity> users;
}
