package info.tduty.typetalkserver.service.parser;

import com.google.gson.Gson;
import info.tduty.typetalkserver.data.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventParser {

    private Gson gson;

    @Autowired
    public EventParser(Gson gson) {
        this.gson = gson;
    }

    public Event parse(String jsonString) {
        return gson.fromJson(jsonString, Event.class);
    }
}
