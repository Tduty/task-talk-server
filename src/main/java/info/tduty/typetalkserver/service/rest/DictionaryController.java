package info.tduty.typetalkserver.service.rest;

import info.tduty.typetalkserver.data.dto.DictionaryDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DictionaryController {

    @GetMapping("/dictionary")
    public List<DictionaryDTO> get(@RequestParam(name = "version") String version) {
        return null;
    }

    @GetMapping("/dictionary/{uuid}")
    public List<DictionaryDTO> getByLessonId(@PathVariable String uuid) {
        return null;
    }
}
