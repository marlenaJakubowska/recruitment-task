package pl.marspc.recruitmenttask.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import pl.marspc.recruitmenttask.dto.UserGitHubResponse;
import reactor.core.publisher.Mono;

@Service
public class UserApiService {

    private final WebClient webClient;

    public UserApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.github.com/users").build();
    }

//    public UserGitHubResponse fetchGitHubUserByLogin(String githubApiUrl, final String login) {
//        RestTemplate restTemplate = new RestTemplate();
//        String apiUrl = String.format("%s%s", githubApiUrl, login);
//        ResponseEntity<UserGitHubResponse> response = restTemplate.getForEntity(apiUrl, UserGitHubResponse.class);
//        return response.getBody();
//    }

    public Mono<UserGitHubResponse> fetchGitHubUserByLogin(String login) {
        return webClient.get()
                .uri("/{login}", login)
                .retrieve()
                .bodyToMono(UserGitHubResponse.class);
    }
}