package ua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ua.entity.Role;
import ua.entity.User;
import ua.repository.UserRepository;

import java.util.List;

@SpringBootApplication
@ImportAutoConfiguration(classes = WebMvcAutoConfiguration.class)
public class Application implements WebMvcConfigurer {

    private static final String ADMIN = "admin";

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
//		addAdmin(run);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setOneIndexedParameters(true);
        argumentResolvers.add(resolver);
        argumentResolvers.add(resolver);
//        super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        return new JavaMailSenderImpl();
    }

    private static void addAdmin(ConfigurableApplicationContext run) {
        UserRepository repository = run.getBean(UserRepository.class);
        User user = repository.findUserByEmail(ADMIN);
        if (user == null) {
            PasswordEncoder encoder = run.getBean(PasswordEncoder.class);
            user = new User();
            user.setEmail(ADMIN);
            user.setPassword(encoder.encode(ADMIN));
            user.setRole(Role.ROLE_ADMIN);
            repository.save(user);
        } else {
            user.setRole(Role.ROLE_ADMIN);
            repository.save(user);
        }
    }
}