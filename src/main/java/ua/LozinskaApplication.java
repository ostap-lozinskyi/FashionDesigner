package ua;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ua.argument.resolver.UserMethodHandlerArgumentResolver;
import ua.entity.Role;
import ua.entity.User;
import ua.repository.UserRepository;

@SpringBootApplication
@ImportAutoConfiguration(classes = WebMvcAutoConfiguration.class)
public class LozinskaApplication extends WebMvcConfigurerAdapter {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(LozinskaApplication.class, args);
		addAdmin(run);
	}
	
	@Autowired
	UserMethodHandlerArgumentResolver userResolver;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
		resolver.setOneIndexedParameters(true);
		argumentResolvers.add(resolver);
		argumentResolvers.add(resolver);
		super.addArgumentResolvers(argumentResolvers);
	}

	static void addAdmin(ConfigurableApplicationContext run) {
		UserRepository repository = run.getBean(UserRepository.class);
		User user = repository.findUserByEmail("admin");
		if (user == null) {
			PasswordEncoder encoder = run.getBean(PasswordEncoder.class);
			user = new User();
			user.setEmail("admin");
			user.setPassword(encoder.encode("admin"));
			user.setRole(Role.ROLE_ADMIN);
			repository.save(user);
		} else {
			user.setRole(Role.ROLE_ADMIN);
			repository.save(user);
		}
	}
	
}
