package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Account;
import lombok.Data;

@Data
public class UserDTO {
    private String accountId;

    private AccountDTO account;

    private String name;

    private String phone;

    private String email;
}
