package pl.marspc.recruitmenttask.service;

import pl.marspc.recruitmenttask.utils.GitHubUser;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<GitHubUser> fetchGitHubUserByLogin(String login);

}
