package info.tduty.typetalkserver.repository.jpa;

import info.tduty.typetalkserver.data.entity.AuthorityEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityJpaRepository extends CrudRepository<AuthorityEntity, String> {

    @Query(value = "SELECT * FROM Authority WHERE name = ?1", nativeQuery = true)
    AuthorityEntity findByUsername(String name);
}
