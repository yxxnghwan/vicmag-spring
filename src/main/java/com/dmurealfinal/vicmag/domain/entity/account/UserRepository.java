package com.dmurealfinal.vicmag.domain.entity.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}