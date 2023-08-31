package pl.marspc.recruitmenttask.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.marspc.recruitmenttask.entity.UserRequest;
import pl.marspc.recruitmenttask.repository.UserRequestRepository;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRequestServiceTest {

    @Mock
    private UserRequestRepository userRequestRepository;

    @InjectMocks
    private UserRequestService userRequestService;

//    @Test
//    void testIncrementUserRequestCount_NewUserRequest() {
//        when(userRequestRepository.findByLogin(anyString())).thenReturn(null);
//
//        userRequestService.incrementUserRequestCount("newToDBUser");
//
//        verify(userRequestRepository).findByLogin("newToDBUser");
//        verify(userRequestRepository).save(argThat(userRequest ->
//                userRequest.getLogin().equals("newToDBUser") && userRequest.getRequestCount() == 1L));
//    }

    @Test
    void testIncrementUserRequestCount_NewUserRequest() {
        when(userRequestRepository.findByLogin(anyString())).thenReturn(Mono.empty()); // Use Mono.empty() here
        when(userRequestRepository.save(any(UserRequest.class))).thenReturn(Mono.just(UserRequest.builder()
                .login("newToDBUser")
                .requestCount(1L)
                .build())); // Use Mono.just here

        userRequestService.incrementUserRequestCount("newToDBUser").block(); // Block to wait for completion

        verify(userRequestRepository).findByLogin("newToDBUser");
        verify(userRequestRepository).save(argThat(userRequest ->
                userRequest.getLogin().equals("newToDBUser") && userRequest.getRequestCount() == 1L));
    }

    @Test
    void testIncrementUserRequestCount_ExistingUserRequest() {
        UserRequest existingUserRequest = UserRequest.builder()
                .login("existingUser")
                .requestCount(5L)
                .build();

        when(userRequestRepository.findByLogin(anyString())).thenReturn(Mono.just(existingUserRequest));
        when(userRequestRepository.save(any(UserRequest.class))).thenReturn(Mono.just(existingUserRequest));

        userRequestService.incrementUserRequestCount("existingUser").block();

        verify(userRequestRepository).findByLogin("existingUser");
        verify(userRequestRepository).save(existingUserRequest);

        verifyNoMoreInteractions(userRequestRepository);

    }
}