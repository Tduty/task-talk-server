package info.tduty.typetalkserver.configuration;

import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.repository.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class H2SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserWrapper userWrapper;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/h2-console/**", "/images/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin();
        httpSecurity.csrf()
                .ignoringAntMatchers("/h2-console/**")
                .disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        UserEntity user = new UserEntity();
        user.setName("user");
        user.setPassword(providePasswordEncoder().encode("pass"));
        user.setEnabled(true);
        userWrapper.add(user, "ROLE_USER");
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select name,password,enabled "
                        + "from user "
                        + "where name = ?")
                .authoritiesByUsernameQuery("select name,authority "
                        + "from authority "
                        + "where name = ?");
    }

    @Bean
    public PasswordEncoder providePasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
