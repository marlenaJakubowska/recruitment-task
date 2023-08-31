package pl.marspc.recruitmenttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.function.client.WebClient;
import pl.marspc.recruitmenttask.controller.UserController;
import pl.marspc.recruitmenttask.utils.GitHubResponseToUserDtoMapper;

@SpringBootApplication
public class RecruitmentTaskApplication {
	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentTaskApplication.class, args);
	}

}
