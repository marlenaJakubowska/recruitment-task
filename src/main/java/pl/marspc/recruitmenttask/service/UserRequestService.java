package pl.marspc.recruitmenttask.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.marspc.recruitmenttask.entity.UserRequest;
import pl.marspc.recruitmenttask.repository.UserRequestRepository;
import reactor.core.publisher.Mono;

@Service
public class UserRequestService {
    private final UserRequestRepository userRequestRepository;

    public UserRequestService(UserRequestRepository userRequestRepository) {
        this.userRequestRepository = userRequestRepository;
    }

//    @Transactional
//    public void incrementUserRequestCount(String login) {
//        Optional<UserRequest> userRequestOptional = Optional.ofNullable(userRequestRepository.findByLogin(login));
//        UserRequest userRequest = userRequestOptional.orElse(UserRequest.builder().requestCount(0L).login(login).build());
//        userRequest.setRequestCount(userRequest.getRequestCount() + 1);
//        userRequestRepository.save(userRequest);
//    }

    @Transactional
    public Mono<Void> incrementUserRequestCount(String login) {
        return userRequestRepository.findByLogin(login)
                .flatMap(userRequest -> {
                    userRequest.setRequestCount(userRequest.getRequestCount() + 1);
                    return userRequestRepository.save(userRequest);
                })
                .switchIfEmpty(
                        userRequestRepository.save(UserRequest.builder()
                                        .login(login)
                                        .requestCount(1L)
                                        .build())
                                .then(userRequestRepository.findByLogin(login))
                )
                .then();
    }
}
