package com.dmurealfinal.vicmag.domain.entity.account;

import com.dmurealfinal.vicmag.domain.dto.UserDTO;
import com.dmurealfinal.vicmag.domain.entity.magazine.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User AS u WHERE u.accountId = :userId")
    User findByUserId(@Param("userId") String userId);

    @Modifying
    @Query("UPDATE User u SET " +
            "u.email = :email, " +
            "u.name = :name, " +
            "u.phone = :phone " +
            "WHERE u.accountId = :userId")
    int updateUser(@Param("userId") String userId, @Param("email") String email, @Param("name") String name, @Param("phone") String phone);
}