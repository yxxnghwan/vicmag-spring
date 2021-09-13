package com.dmurealfinal.vicmag.domain.entity.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface AccountRepository extends JpaRepository<Account, String> {
    @Modifying
    @Query("UPDATE Account a SET " +
            "a.password = :password, " +
            "a.modifiedDateTime = :now " +
            "WHERE a.accountId = :accountId")
    int updatePassword(@Param("accountId") String accountId, @Param("password") String password, @Param("now") LocalDateTime now);
}