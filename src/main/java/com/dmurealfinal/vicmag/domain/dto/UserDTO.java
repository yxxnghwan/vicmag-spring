package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Account;
import com.dmurealfinal.vicmag.domain.entity.account.User;
import lombok.Data;

@Data
public class UserDTO {
    private String accountId;

    private AccountDTO account;

    private String name;

    private String phone;

    private String email;

    public User toEntity() {
        return User.builder()
                .accountId(this.accountId)
                .name(this.name)
                .phone(this.phone)
                .email(this.email)
                .build();
    }
}
