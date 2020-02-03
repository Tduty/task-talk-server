package info.tduty.typetalkserver.service.rest;

import info.tduty.typetalkserver.data.dto.LessonDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LessonController {

    @GetMapping("/lessons")
    public List<LessonDTO> get() {
        return null;
    }

    @GetMapping("/lessons/{uuid}")
    public List<LessonDTO> getByLessonId(@PathVariable String uuid) {
        return null;
    }
}
