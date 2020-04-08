package info.tduty.typetalkserver.service.rest;

import info.tduty.typetalkserver.data.dto.DictionaryDTO;
import info.tduty.typetalkserver.domain.interactor.DictionaryInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class DictionaryController {

    private DictionaryInteractor dictionaryInteractor;

    @Autowired
    public DictionaryController(DictionaryInteractor dictionaryInteractor) {
        this.dictionaryInteractor = dictionaryInteractor;
    }

    @GetMapping("/dictionary")
    public List<DictionaryDTO> get(@RequestParam(name = "version") String version, Principal principal) {
        return dictionaryInteractor.getAll(principal.getName());
    }

    @GetMapping("/dictionary/{uuid}")
    public List<DictionaryDTO> getByLessonId(@PathVariable String uuid) {
        return dictionaryInteractor.getByLessonId(uuid);
    }
}
