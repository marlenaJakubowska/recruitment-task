package pl.marspc.recruitmenttask.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import pl.marspc.recruitmenttask.entity.UserRequest;

@Repository
public interface UserRequestRepository extends ReactiveCrudRepository<UserRequest, String> {
    @Query("SELECT user_requests.login, user_requests.request_count FROM user_requests WHERE user_requests.login = :login")
    Mono<UserRequest> findByLogin(String login);
}
