package info.tduty.typetalkserver.repository;

import info.tduty.typetalkserver.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends CrudRepository<UserEntity, Long> {
}
