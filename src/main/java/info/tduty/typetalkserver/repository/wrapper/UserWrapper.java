package info.tduty.typetalkserver.repository.wrapper;

import info.tduty.typetalkserver.data.entity.AuthorityEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.repository.jpa.AuthorityJpaRepository;
import info.tduty.typetalkserver.repository.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserWrapper {

    private UserJpaRepository userJpaRepository;
    private AuthorityJpaRepository authorityJpaRepository;

    @Autowired
    public UserWrapper(UserJpaRepository userJpaRepository,
                       AuthorityJpaRepository authorityJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.authorityJpaRepository = authorityJpaRepository;
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

    public UserEntity add(UserEntity user, String rule) {
        AuthorityEntity authority = new AuthorityEntity();
        authority.setName(user.getName());
        authority.setAuthority(rule);
        authorityJpaRepository.save(authority);
        return userJpaRepository.save(user);
    }
}
