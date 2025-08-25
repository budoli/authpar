package com.test.authCRUD.repository;

import com.test.authCRUD.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
