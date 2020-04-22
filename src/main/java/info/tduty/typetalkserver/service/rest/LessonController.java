package info.tduty.typetalkserver.service.rest;

import info.tduty.typetalkserver.data.dto.CreateDialogDTO;
import info.tduty.typetalkserver.data.dto.LessonDTO;
import info.tduty.typetalkserver.data.dto.TeacherLessonDTO;
import info.tduty.typetalkserver.data.dto.TeacherTaskDTO;
import info.tduty.typetalkserver.domain.interactor.LessonInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class LessonController {

    private LessonInteractor lessonInteractor;

    @Autowired
    public LessonController(LessonInteractor lessonInteractor) {
        this.lessonInteractor = lessonInteractor;
    }

    @GetMapping("/lessons")
    public List<LessonDTO> get(Principal principal) {
        return lessonInteractor.getLessons(principal.getName());
    }

    @GetMapping("/lessons/teacher/{class_id}")
    public List<TeacherLessonDTO> getByTeacherLessons(@PathVariable(name = "class_id")String classId,
                                                      Principal principal) {
        return lessonInteractor.getTeacherLessons(principal.getName(), classId);
    }

    @GetMapping("/lessons/tasks/teacher/{lesson_id}")
    public List<TeacherTaskDTO> getByTeacherTasks(@PathVariable(name = "lesson_id") String lessonId,
                                                  @RequestParam(name = "class_id") String classId,
                                                  Principal principal) {
        return lessonInteractor.getTeacherTasks(principal.getName(), lessonId, classId);
    }

    @GetMapping("/lessons/{uuid}")
    public LessonDTO getByLessonId(@PathVariable String uuid, Principal principal) {
        return lessonInteractor.getLessonById(principal.getName(), uuid);
    }

    @PostMapping("/lessons/create_dialogs")
    public void createDialogs(@RequestBody CreateDialogDTO createDialogDTO) {
        lessonInteractor.createDialogs(createDialogDTO);
    }
}
