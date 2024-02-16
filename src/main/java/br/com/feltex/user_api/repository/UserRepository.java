package br.com.feltex.user_api.repository;

import br.com.feltex.user_api.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {

    List<UsersEntity> findByEmailContainingAndUsernameContaining(String email, String username);

    Boolean existsByEmail(String email);

    @Query("SELECT user FROM UsersEntity user WHERE user.school.schoolId IN :schoolIds")
    List<UsersEntity> findAllBySchoolIds(List<Long> schoolIds);

}
