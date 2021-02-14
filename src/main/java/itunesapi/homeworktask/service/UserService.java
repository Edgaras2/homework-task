package itunesapi.homeworktask.service;

import itunesapi.homeworktask.entity.User;
import itunesapi.homeworktask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service is used to save user into H2db
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }
}
