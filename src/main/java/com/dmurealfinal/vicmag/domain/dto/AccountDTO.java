package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Account;
import com.dmurealfinal.vicmag.domain.entity.account.Company;
import com.dmurealfinal.vicmag.domain.entity.account.User;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToOne;

@Data
public class AccountDTO {
    private String accountId;

    private String password;

    private String accountType;


    // for response
    private UserDTO user;
    private CompanyDTO company;


    public Account toEntity() {
        return Account.builder()
                .accountId(this.accountId)
                .password(this.password)
                .accountType(this.accountType)
                .build();
    }
}
