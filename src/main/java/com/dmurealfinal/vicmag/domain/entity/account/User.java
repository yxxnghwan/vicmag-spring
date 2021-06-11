package com.dmurealfinal.vicmag.domain.entity.account;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "TB_USER")
@Entity
public class User {
    @Id
    @Column(columnDefinition = "nvarchar(30)")
    private String accountId;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Account account;

    @Column(columnDefinition = "nvarchar(30)", nullable = false)
    private String name;

    @Column(columnDefinition = "nvarchar(15)")
    private String phone;

    @Column(columnDefinition = "nvarchar(100)")
    private String email;

    public User() {}

    @Builder
    public User(String accountId, Account account, String name, String phone, String email) {
        this.accountId = accountId;
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
