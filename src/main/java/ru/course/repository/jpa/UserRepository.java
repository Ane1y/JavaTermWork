package ru.course.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.course.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserName(String userName);
}
