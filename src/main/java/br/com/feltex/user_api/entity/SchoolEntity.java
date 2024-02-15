package br.com.feltex.user_api.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class SchoolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "school_id")
    private Long schoolId;

    @NotNull
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<UserEntity> users;
}
