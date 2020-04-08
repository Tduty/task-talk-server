package info.tduty.typetalkserver.domain.interactor;

import info.tduty.typetalkserver.data.dto.DictionaryDTO;
import info.tduty.typetalkserver.data.entity.DictionaryEntity;
import info.tduty.typetalkserver.repository.wrapper.DictionaryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DictionaryInteractor {

    private DictionaryWrapper dictionaryWrapper;

    @Autowired
    public DictionaryInteractor(DictionaryWrapper dictionaryWrapper) {
        this.dictionaryWrapper = dictionaryWrapper;
    }

    public List<DictionaryDTO> getAll(String username) {
        List<DictionaryEntity> dictionaries = dictionaryWrapper.getAllByUsername(username);
        return dictionaries.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<DictionaryDTO> getByLessonId(String lessonId) {
        List<DictionaryEntity> dictionaries = dictionaryWrapper.getByLessonId(lessonId);
        return dictionaries.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private DictionaryDTO mapToDTO(DictionaryEntity dictionary) {
        return new DictionaryDTO(
                dictionary.getId(),
                dictionary.getLesson().getId(),
                dictionary.getLesson().getTitle(),
                dictionary.getContent(),
                dictionary.getTranslation(),
                dictionary.getTranscription()
        );
    }
}
