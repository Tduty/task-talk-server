package info.tduty.typetalkserver.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import info.tduty.typetalkserver.data.event.Event;
import info.tduty.typetalkserver.data.serializer.EventDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public Gson createGson() {
        Gson basicGson = new GsonBuilder().create();

        return new GsonBuilder()
                .registerTypeAdapter(Event.class, new EventDeserializer(basicGson))
                .create();
    }
}
