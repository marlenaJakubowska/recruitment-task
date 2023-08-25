package pl.marspc.recruitmenttask.dto;

import lombok.*;

@Data
@Builder
public class UserDto {
    private long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private String createdAt;
    private double calculations;

}
