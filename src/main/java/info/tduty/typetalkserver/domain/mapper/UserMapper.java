package info.tduty.typetalkserver.domain.mapper;

import info.tduty.typetalkserver.data.dto.CreateClassDTO;
import info.tduty.typetalkserver.data.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity dtoToDB(CreateClassDTO.UserDTO user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setSex(user.getSex());
        userEntity.setTeacher(false);
        return userEntity;
    }
}
