package itunesapi.homeworktask.service;

import itunesapi.homeworktask.entity.User;
import itunesapi.homeworktask.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService classUnderTest = new UserService();;

    @Test
    public void save_user_whenUserServiceIsCalled() {
        // Given
        User user = new User();

        // When
        classUnderTest.saveOrUpdate(user);

        // Then
        verify(userRepository).save(user);
    }

}