package info.tduty.typetalkserver.repository.jpa;

import info.tduty.typetalkserver.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserJpaRepository extends CrudRepository<UserEntity, String> {
}
