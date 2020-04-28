package info.tduty.typetalkserver.domain.mapper.object;

public class WordamessObject {

    private String correct_body;
    private String body;

    public WordamessObject(String correct_body, String body) {
        this.correct_body = correct_body;
        this.body = body;
    }

    public String getCorrect_body() {
        return correct_body;
    }

    public String getBody() {
        return body;
    }
}
