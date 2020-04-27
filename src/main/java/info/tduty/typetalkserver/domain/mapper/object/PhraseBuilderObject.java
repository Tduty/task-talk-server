package info.tduty.typetalkserver.domain.mapper.object;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PhraseBuilderObject {

    private String phrases;

    public PhraseBuilderObject(String phrases) {
        this.phrases = phrases;
    }

    public List<String> getPhrases() {
        return Arrays.asList(phrases.split("/"));
    }
}
