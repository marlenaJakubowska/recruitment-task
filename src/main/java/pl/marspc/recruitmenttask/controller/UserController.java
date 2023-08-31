package pl.marspc.recruitmenttask.controller;

import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.marspc.recruitmenttask.dto.UserGitHubResponse;
import pl.marspc.recruitmenttask.dto.UserDto;
import pl.marspc.recruitmenttask.service.UserRequestService;
import pl.marspc.recruitmenttask.service.UserApiService;
import pl.marspc.recruitmenttask.utils.GitHubResponseToUserDtoMapper;

@Validated
@RestController
public class UserController {

    private final UserApiService userApiService;
    private final UserRequestService userRequestService;
    private final GitHubResponseToUserDtoMapper gitHubResponseToUserDtoMapper;

    @Value("${api.url.github}")
    private String githubApiUrl;

    public UserController(UserApiService userApiService, UserRequestService userRequestService, GitHubResponseToUserDtoMapper gitHubResponseToUserDtoMapper) {
        this.userApiService = userApiService;
        this.userRequestService = userRequestService;
        this.gitHubResponseToUserDtoMapper = new GitHubResponseToUserDtoMapper();
    }

    @GetMapping(path = "/users/{login}", produces = "application/json")
    public ResponseEntity<UserDto> getUserData(@PathVariable(value = "login") @Pattern(regexp="^[a-zA-Z0-9-]+$",
                                                           message = "Please provide correct login") String login){
        UserGitHubResponse userGitHubResponse = userApiService.fetchGitHubUserByLogin(githubApiUrl, login);
        userRequestService.incrementUserRequestCount(login);
        return ResponseEntity.ok(gitHubResponseToUserDtoMapper.mapUserApiResponseToUserResponseDTO(userGitHubResponse));
    }
}