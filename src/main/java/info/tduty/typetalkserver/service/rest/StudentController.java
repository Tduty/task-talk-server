package info.tduty.typetalkserver.service.rest;

import info.tduty.typetalkserver.data.dto.StudentDTO;
import info.tduty.typetalkserver.domain.interactor.ClassInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class StudentController {

    private ClassInteractor classInteractor;

    @Autowired
    public StudentController(ClassInteractor classInteractor) {
        this.classInteractor = classInteractor;
    }

    @GetMapping("/student/{uuid}")
    public List<StudentDTO> getByClassId(@PathVariable String uuid, Principal principal) {
        return classInteractor.get(principal.getName(), uuid);
    }
}
