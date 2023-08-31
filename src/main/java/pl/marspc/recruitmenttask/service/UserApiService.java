package pl.marspc.recruitmenttask.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.marspc.recruitmenttask.dto.UserGitHubResponse;

@Service
public class UserApiService {

    public UserGitHubResponse fetchGitHubUserByLogin(String githubApiUrl, final String login) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = String.format("%s%s", githubApiUrl, login);
        ResponseEntity<UserGitHubResponse> response = restTemplate.getForEntity(apiUrl, UserGitHubResponse.class);
        return response.getBody();
    }
}

