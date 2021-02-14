package itunesapi.homeworktask.repository;

import itunesapi.homeworktask.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
