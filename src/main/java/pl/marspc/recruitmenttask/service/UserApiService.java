package pl.marspc.recruitmenttask.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.marspc.recruitmenttask.dto.UserGitHubResponse;
import pl.marspc.recruitmenttask.exception.UserDoesNotExistException;

@Service
public class UserApiService {

    public UserGitHubResponse fetchGitHubUserByLogin(final String login) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = String.format("https://api.github.com/users/%s", login);

        try {
            ResponseEntity<UserGitHubResponse> response = restTemplate.getForEntity(apiUrl, UserGitHubResponse.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else if (response.getStatusCode().is4xxClientError()) {
                throw new UserDoesNotExistException();
            } else {
                throw new RuntimeException("GitHub API request failed with status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch data from GitHub", e);
        }
    }

}
