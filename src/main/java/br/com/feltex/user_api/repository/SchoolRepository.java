package br.com.feltex.user_api.repository;

import br.com.feltex.user_api.entity.SchoolsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolsEntity, Long> {

//    @Query("select * from school_entity Left_join ")
//    List<SchoolEntity> findAllBy
}
