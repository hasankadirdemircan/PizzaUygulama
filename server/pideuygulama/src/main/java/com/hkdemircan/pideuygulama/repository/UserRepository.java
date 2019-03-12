package com.hkdemircan.pideuygulama.repository;

import com.hkdemircan.pideuygulama.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {

    User findFirstById(Long id);
}
