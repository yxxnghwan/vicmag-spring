package com.dmurealfinal.vicmag.domain.entity.account;

import com.dmurealfinal.vicmag.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "TB_ACCOUNT")
@Entity
public class Account extends BaseTimeEntity {

    @Id
    @Column(columnDefinition = "nvarchar(30)")
    private String accountId;

    @Column(columnDefinition = "nvarchar(300)")
    private String password;

    @Column(columnDefinition = "nvarchar(10)")
    private String accountType;

    @OneToOne(mappedBy = "account")
    private Company company;

    @OneToOne(mappedBy = "account")
    private User user;

    public Account() {}

    @Builder
    public Account(String accountId, String password, String accountType) {
        this.accountId = accountId;
        this.password = password;
        this.accountType = accountType;
    }
}
