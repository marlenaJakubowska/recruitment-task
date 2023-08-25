package pl.marspc.recruitmenttask.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.marspc.recruitmenttask.entity.UserRequest;
import pl.marspc.recruitmenttask.repository.UserRequestRepository;

import java.util.Optional;

@Service
public class UserRequestService {
    private final UserRequestRepository userRequestRepository;

    public UserRequestService(UserRequestRepository userRequestRepository) {
        this.userRequestRepository = userRequestRepository;
    }

    @Transactional
    public void incrementUserRequestCount(String login) {
        Optional<UserRequest> userRequestOptional = Optional.ofNullable(userRequestRepository.findByLogin(login));
        UserRequest userRequest = userRequestOptional.orElse(UserRequest.builder().requestCount(0L).login(login).build());
        userRequest.setRequestCount(userRequest.getRequestCount() + 1);
        userRequestRepository.save(userRequest);
    }
}
