package com.hkdemircan.pideuygulama.repository;

import com.hkdemircan.pideuygulama.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, Long> {

    User findFirstById(Long id);
    User findFirstByEmail(String email);
}
