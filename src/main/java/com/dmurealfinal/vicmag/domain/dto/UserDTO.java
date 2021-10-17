package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Account;
import com.dmurealfinal.vicmag.domain.entity.account.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserDTO {
    private String accountId;

    private AccountDTO account;

    private String name;

    private String phone;

    private String email;

    private KakaoAccountDTO kakaoAccount;

    public UserDTO() {}

    @Builder
    public UserDTO(String accountId, AccountDTO account, String name, String phone, String email, KakaoAccountDTO kakaoAccount) {
        this.accountId = accountId;
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.kakaoAccount = kakaoAccount;
    }

    public User toEntity() {
        return User.builder()
                .accountId(this.accountId)
                .name(this.name)
                .phone(this.phone)
                .email(this.email)
                .build();
    }
}
