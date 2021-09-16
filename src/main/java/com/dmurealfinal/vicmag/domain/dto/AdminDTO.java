package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Admin;
import lombok.Builder;
import lombok.Data;

@Data
public class AdminDTO {
    private String accountId;

    private AccountDTO account;

    private String name;

    private String department;

    private String nickName;

    @Builder
    public AdminDTO(String accountId, String name, String department, String nickName, AccountDTO account) {
        this.accountId = accountId;
        this.name = name;
        this.department = department;
        this.nickName = nickName;
        this.account = account;
    }

    public Admin toEntity() {
        return Admin.builder()
                .accountId(this.accountId)
                .name(this.name)
                .department(this.department)
                .nickName(this.nickName)
                .build();
    }
}
