package info.tduty.typetalkserver.repository.wrapper;

import info.tduty.typetalkserver.data.entity.ClassEntity;
import info.tduty.typetalkserver.repository.jpa.ClassJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClassWrapper {

    private ClassJpaRepository classJpaRepository;

    @Autowired
    public ClassWrapper(ClassJpaRepository classJpaRepository) {
        this.classJpaRepository = classJpaRepository;
    }

    public ClassEntity add(ClassEntity classEntity) {
        return classJpaRepository.save(classEntity);
    }

    public Optional<ClassEntity> get(String id) {
        return classJpaRepository.findById(id);
    }
}
