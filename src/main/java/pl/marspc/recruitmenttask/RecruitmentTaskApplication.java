package pl.marspc.recruitmenttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.marspc.recruitmenttask.controller.UserController;
import pl.marspc.recruitmenttask.utils.GitHubResponseToUserDtoMapper;

@SpringBootApplication
public class RecruitmentTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentTaskApplication.class, args);
	}

}
