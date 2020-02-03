package info.tduty.typetalkserver.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public Gson createGson() {
        return new GsonBuilder()
                .create();
    }
}
