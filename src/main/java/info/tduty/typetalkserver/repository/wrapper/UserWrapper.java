package info.tduty.typetalkserver.repository.wrapper;

import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.repository.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserWrapper {

    private UserJpaRepository userJpaRepository;

    @Autowired
    public UserWrapper(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public Optional<UserEntity> get(String id) {
        return userJpaRepository.findById(id);
    }

    public boolean isExist(String id) {
        return userJpaRepository.existsById(id);
    }

    public UserEntity add(UserEntity user) {
        return userJpaRepository.save(user);
    }
}
