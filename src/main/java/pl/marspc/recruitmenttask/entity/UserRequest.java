package pl.marspc.recruitmenttask.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@Validated
@Table(name = "user_requests")
public class UserRequest {

    @Id
    @Column("login")
    @NotEmpty(message = "Please provide login")
    private String login;

    @Column("request_count")
    private Long requestCount;

    public UserRequest(String login, Long requestCount) {
        this.login = login;
        this.requestCount = requestCount;
    }

    public UserRequest() {
    }
}
