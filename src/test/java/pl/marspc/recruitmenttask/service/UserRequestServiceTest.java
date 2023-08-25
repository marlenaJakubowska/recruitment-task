package pl.marspc.recruitmenttask.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.marspc.recruitmenttask.entity.UserRequest;
import pl.marspc.recruitmenttask.repository.UserRequestRepository;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRequestServiceTest {

    @Mock
    private UserRequestRepository userRequestRepository;

    @InjectMocks
    private UserRequestService userRequestService;

    @Test
    void testIncrementUserRequestCount_NewUserRequest() {
        when(userRequestRepository.findByLogin(anyString())).thenReturn(null);

        userRequestService.incrementUserRequestCount("newToDBUser");

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

        when(userRequestRepository.findByLogin(anyString())).thenReturn(existingUserRequest);

        userRequestService.incrementUserRequestCount("existingUser");

        verify(userRequestRepository).findByLogin("existingUser");
        verify(userRequestRepository).save(existingUserRequest);
    }
}