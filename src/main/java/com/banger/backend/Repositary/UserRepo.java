package com.banger.backend.Repositary;

import com.banger.backend.Entity.Equipment;
import com.banger.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    User findUserByEmail(String email);

    Optional<User> findByNicNumber(String itemName);
}
