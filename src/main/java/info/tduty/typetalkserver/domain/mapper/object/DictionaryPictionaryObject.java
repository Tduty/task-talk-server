package info.tduty.typetalkserver.domain.mapper.object;

public class DictionaryPictionaryObject {

    private String translate;
    private String image;

    public DictionaryPictionaryObject(String translate, String image) {
        this.translate = translate;
        this.image = image;
    }

    public String getTranslate() {
        return translate;
    }

    public String getImage() {
        return image;
    }
}
