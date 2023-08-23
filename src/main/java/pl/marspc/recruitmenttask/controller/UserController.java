package pl.marspc.recruitmenttask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pl.marspc.recruitmenttask.dto.UserResponse;
import pl.marspc.recruitmenttask.service.UserServiceImpl;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{login}")
    public Mono<ResponseEntity<?>> getUserInfo(@PathVariable String login) {
        return userService.fetchGitHubUserByLogin(login)
                .flatMap(gitHubUser -> {
                    UserResponse userResponse = new UserResponse();
                    userResponse.setId(gitHubUser.getId());
                    userResponse.setLogin(gitHubUser.getLogin());
                    userResponse.setName(gitHubUser.getName());
                    userResponse.setAvatarUrl(gitHubUser.getAvatarUrl());
                    userResponse.setCreatedAt(gitHubUser.getCreatedAt());

                    try {
                        double calculations = 6.0 / (gitHubUser.getFollowers() * (2 + gitHubUser.getPublicRepos()));
                        userResponse.setCalculations(calculations);
                    } catch (ArithmeticException e) {
                        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error calculating the 'calculations' field."));
                    }

                    return Mono.just(ResponseEntity.ok(userResponse));
                })
                .switchIfEmpty(Mono.defer(() -> Mono.just(ResponseEntity.notFound().build())))
                .onErrorResume(WebClientResponseException.class, ex -> {
                    if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.just(ResponseEntity.notFound().build());
                    } else {
                        String errorMessage = "GitHub API responded with an error: " + ex.getResponseBodyAsString();
                        return Mono.just(ResponseEntity.status(ex.getStatusCode()).body(errorMessage));
                    }
                })
                .onErrorResume(Exception.class, ex ->
                        Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }
}