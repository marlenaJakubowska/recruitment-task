package pl.marspc.recruitmenttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.marspc.recruitmenttask.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin(String login);
}
