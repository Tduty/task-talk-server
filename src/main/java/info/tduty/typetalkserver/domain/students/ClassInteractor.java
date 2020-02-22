package info.tduty.typetalkserver.domain.students;

import info.tduty.typetalkserver.data.entity.ClassEntity;
import info.tduty.typetalkserver.repository.wrapper.ClassWrapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ClassInteractor {

    private ClassWrapper classWrapper;

    @Autowired
    public ClassInteractor(ClassWrapper classWrapper) {
        this.classWrapper = classWrapper;
    }

    public void createClass(String title) {
        ClassEntity classEntity = classWrapper.add(title);

    }
}
