package info.tduty.typetalkserver.service.rest;

import info.tduty.typetalkserver.data.dto.ClassCreatedDTO;
import info.tduty.typetalkserver.data.dto.CreateClassDTO;
import info.tduty.typetalkserver.domain.interactor.ClassInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassController {

    private ClassInteractor classInteractor;

    @Autowired
    public ClassController(ClassInteractor classInteractor) {
        this.classInteractor = classInteractor;
    }

    @PutMapping("/create_class")
    public ClassCreatedDTO createClass(@RequestBody CreateClassDTO createClass) {
        return classInteractor.create(createClass);
    }
}
