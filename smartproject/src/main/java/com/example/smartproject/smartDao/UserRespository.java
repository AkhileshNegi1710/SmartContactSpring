package com.example.smartproject.smartDao;

import com.example.smartproject.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<UserEntity, Long> {
}
