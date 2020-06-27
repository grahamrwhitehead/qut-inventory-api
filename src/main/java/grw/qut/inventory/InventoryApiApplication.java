package grw.qut.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class InventoryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApiApplication.class, args);
    }

    @Configuration
    public static class CorsConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(final CorsRegistry registry) {
            registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").exposedHeaders("Authorization");
        }
    }

    @Configuration
    public static class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().hasRole("USER").and()
                .httpBasic().and()
                .cors().and()
                .csrf().disable()
                .headers().frameOptions().disable();
        }

        @Autowired
        public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                .withUser("user1").password("{noop}password").roles("USER").and()
                .withUser("user2").password("{noop}password").roles("USER").and()
                .withUser("user3").password("{noop}password").roles("USER").and()
                .withUser("forbiddenUser1").password("{noop}password").roles("FORBIDDEN");
        }
    }
}
