package br.com.feltex.user_api.repository;

import br.com.feltex.user_api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByEmailContainingAndUsernameContaining(String email, String username);

    Boolean existsByEmail(String email);

    UserEntity findFirstByEmail(String email);

}
