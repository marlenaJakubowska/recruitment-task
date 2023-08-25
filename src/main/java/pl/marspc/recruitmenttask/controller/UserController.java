package pl.marspc.recruitmenttask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.marspc.recruitmenttask.dto.UserGitHubResponse;
import pl.marspc.recruitmenttask.dto.UserDto;
import pl.marspc.recruitmenttask.service.UserRequestService;
import pl.marspc.recruitmenttask.service.UserApiService;
import pl.marspc.recruitmenttask.utils.GitHubResponseToUserDtoMapper;

@RestController
public class UserController {

    private final UserApiService userApiService;
    private final UserRequestService userRequestService;
    private final GitHubResponseToUserDtoMapper gitHubResponseToUserDtoMapper;

    public UserController(UserApiService userApiService, UserRequestService userRequestService, GitHubResponseToUserDtoMapper gitHubResponseToUserDtoMapper) {
        this.userApiService = userApiService;
        this.userRequestService = userRequestService;
        this.gitHubResponseToUserDtoMapper = new GitHubResponseToUserDtoMapper();
    }

    @GetMapping(path = "/users/{login}", produces = "application/json")
    public ResponseEntity<UserDto> getUserData(@PathVariable(value = "login") String login){
        UserGitHubResponse userGitHubResponse = userApiService.fetchGitHubUserByLogin(login);
        userRequestService.incrementUserRequestCount(login);
        return ResponseEntity.ok(gitHubResponseToUserDtoMapper.mapUserApiResponseToUserResponseDTO(userGitHubResponse));
    }
}