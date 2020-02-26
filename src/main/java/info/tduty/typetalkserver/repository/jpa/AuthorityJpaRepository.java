package info.tduty.typetalkserver.repository.jpa;

import info.tduty.typetalkserver.data.entity.AuthorityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityJpaRepository extends CrudRepository<AuthorityEntity, String> {
}
