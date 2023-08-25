package pl.marspc.recruitmenttask.utils;

import org.springframework.stereotype.Component;
import pl.marspc.recruitmenttask.dto.UserGitHubResponse;
import pl.marspc.recruitmenttask.dto.UserDto;

@Component
public class GitHubResponseToUserDtoMapper {

    public UserDto mapUserApiResponseToUserResponseDTO(UserGitHubResponse userGitHubResponse) {
        return UserDto.builder()
                .id(userGitHubResponse.getId())
                .login(userGitHubResponse.getLogin())
                .name(userGitHubResponse.getName())
                .type(userGitHubResponse.getType())
                .avatarUrl(userGitHubResponse.getAvatarUrl())
                .createdAt(String.valueOf(userGitHubResponse.getCreatedAt()))
                .calculations(calculate(userGitHubResponse))
                .build();
    }

    private double calculate(UserGitHubResponse userGitHubResponse) {
        if (userGitHubResponse.getFollowers() == 0) {
            return 0;
        } else {
            //calculating from left to right
            return 6.00 / userGitHubResponse.getFollowers() * (2 + userGitHubResponse.getPublicRepos());
        }
    }
}
