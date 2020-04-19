package info.tduty.typetalkserver.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import info.tduty.typetalkserver.data.event.Event;
import info.tduty.typetalkserver.data.serializer.EventDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class ServiceConfiguration {

    @Bean
    public Gson createGson() {
        Gson basicGson = new GsonBuilder().create();

        return new GsonBuilder()
                .registerTypeAdapter(Event.class, new EventDeserializer(basicGson))
                .create();
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        System.out.println("inside logging filter");
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(false);
        return loggingFilter;
    }
}
