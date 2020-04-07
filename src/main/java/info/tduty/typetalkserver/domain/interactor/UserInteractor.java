package info.tduty.typetalkserver.domain.interactor;

import info.tduty.typetalkserver.data.dto.UserDTO;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.repository.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInteractor {

    private UserWrapper userWrapper;

    @Autowired
    public UserInteractor(UserWrapper userWrapper) {
        this.userWrapper = userWrapper;
    }

    public UserDTO getUser(String username) {
        Optional<UserEntity> userEntity = userWrapper.getByUsername(username);
        return userEntity.map(this::mapToDTO).orElse(null);
    }

    private UserDTO mapToDTO(UserEntity entity) {
        return new UserDTO(
                entity.getId(),
                entity.getName(),
                entity.getClassEntity() != null ? entity.getClassEntity().getTitle() : null
        );
    }
}
