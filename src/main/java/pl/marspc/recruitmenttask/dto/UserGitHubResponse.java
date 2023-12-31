package pl.marspc.recruitmenttask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserGitHubResponse {
    private Long id;
    private String name;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    private String type;
    @JsonProperty("created_at")
    private String createdAt;
    private String login;
    private int followers;
    @JsonProperty("public_repos")
    private int publicRepos;
}
