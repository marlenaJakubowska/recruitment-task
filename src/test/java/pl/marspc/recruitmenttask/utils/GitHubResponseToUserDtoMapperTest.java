package pl.marspc.recruitmenttask.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.marspc.recruitmenttask.dto.UserDto;
import pl.marspc.recruitmenttask.dto.UserGitHubResponse;

class GitHubResponseToUserDtoMapperTest {

    @Mock
    private UserGitHubResponse userGitHubResponse;

    private GitHubResponseToUserDtoMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mapper = new GitHubResponseToUserDtoMapper();
    }

    @Test
    void testMapUserApiResponseToUserResponseDTO() {
        when(userGitHubResponse.getId()).thenReturn(123L);
        when(userGitHubResponse.getLogin()).thenReturn("octocat");
        when(userGitHubResponse.getName()).thenReturn("Octo Cat");
        when(userGitHubResponse.getType()).thenReturn("User");
        when(userGitHubResponse.getAvatarUrl()).thenReturn("https://avatar-url");
        when(userGitHubResponse.getCreatedAt()).thenReturn(("2011-01-25T18:44:36Z"));
        when(userGitHubResponse.getFollowers()).thenReturn(10226);
        when(userGitHubResponse.getPublicRepos()).thenReturn(8);

        UserDto userDto = mapper.mapUserApiResponseToUserResponseDTO(userGitHubResponse);

        assertEquals(123, userDto.getId());
        assertEquals("octocat", userDto.getLogin());
        assertEquals("Octo Cat", userDto.getName());
        assertEquals("User", userDto.getType());
        assertEquals("https://avatar-url", userDto.getAvatarUrl());
        assertEquals("2011-01-25T18:44:36Z", userDto.getCreatedAt());
        assertEquals(0.00586739683160571, userDto.getCalculations()); // Assuming calculation result is 3.0 for this example
    }

    @Test
    void testCalculateWithFollowersZero() {
        when(userGitHubResponse.getFollowers()).thenReturn(0);

        double result = mapper.calculate(userGitHubResponse);

        assertEquals(0.0, result);
    }

    @Test
    void testCalculateWithFollowersNonZero() {
        when(userGitHubResponse.getFollowers()).thenReturn(6);
        when(userGitHubResponse.getPublicRepos()).thenReturn(22);

        double result = mapper.calculate(userGitHubResponse);

        assertEquals(24, result);
    }
}
