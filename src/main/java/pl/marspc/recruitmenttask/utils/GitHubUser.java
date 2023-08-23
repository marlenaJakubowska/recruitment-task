package pl.marspc.recruitmenttask.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GitHubUser {

private int id;

    private String login;
    private String avatarUrl;
    private int followers;
    private int publicRepos;
    private String createdAt;
    private String name;



}
