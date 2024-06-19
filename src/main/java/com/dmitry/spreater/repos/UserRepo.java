package com.dmitry.spreater.repos;

import com.dmitry.spreater.domain.Message;
import com.dmitry.spreater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
