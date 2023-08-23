package pl.marspc.recruitmenttask.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.marspc.recruitmenttask.dto.UserResponse;
import pl.marspc.recruitmenttask.entity.User;
import pl.marspc.recruitmenttask.repository.UserRepository;
import pl.marspc.recruitmenttask.utils.GitHubUser;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final WebClient webClient;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, WebClient.Builder webClientBuilder) {
        this.userRepository = userRepository;
        this.webClient = webClientBuilder.baseUrl("https://api.github.com").build();
    }

    @Override
    public Mono<GitHubUser> fetchGitHubUserByLogin(String login) {
        return webClient.get()
                .uri("/users/{login}", login)
                .retrieve()
                .bodyToMono(GitHubUser.class);
    }

    @Transactional
    public void incrementRequestCount(String login) {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            user.setRequestCount(user.getRequestCount() + 1);
            userRepository.save(user);
        }
    }
}
