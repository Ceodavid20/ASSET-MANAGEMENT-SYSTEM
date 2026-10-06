package com.asset.ASSET.MANAGEMENT.SYSTEM.Repository;

import com.asset.ASSET.MANAGEMENT.SYSTEM.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}