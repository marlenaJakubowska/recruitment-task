package pl.marspc.recruitmenttask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "user_requests")
@AllArgsConstructor
public class UserRequest {

    @Id
    @Column(name = "LOGIN")
    @NotBlank(message = "Please provide login")
    private String login;

    @Column(name = "REQUEST_COUNT")
    private Long requestCount;

    public UserRequest() {
    }
}
