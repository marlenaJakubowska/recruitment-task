package pl.marspc.recruitmenttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.marspc.recruitmenttask.entity.UserRequest;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, String> {
    UserRequest findByLogin(String login);
}
