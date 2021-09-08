package com.dmurealfinal.vicmag.domain.entity.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KaKaoAccountRepository extends JpaRepository<KaKaoAccount, String> {
    @Query("SELECT ka FROM KaKaoAccount AS ka WHERE ka.kakaoIdNumber = :kakaoIdNumber OR ka.accountId = :accountId")
    KaKaoAccount findByKakaoIdNumberAndAccountId(@Param("kakaoIdNumber") Long kakaoIdNumber, @Param("accountId") String accountId);

    @Query("SELECT a FROM Account AS a, KaKaoAccount AS ka " +
            "WHERE a.accountId = ka.accountId " +
            "AND ka.kakaoIdNumber = :kakaoIdNumber")
    Account findAccountByKakaoIdNumber(@Param("kakaoIdNumber") Long kakaoIdNumber);
}