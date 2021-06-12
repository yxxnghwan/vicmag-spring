package com.dmurealfinal.vicmag.domain.entity.account;

import com.dmurealfinal.vicmag.domain.dto.UserDTO;
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

    @OneToOne
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
    public User(String accountId, String name, String phone, String email) {
        this.accountId = accountId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public UserDTO toDTO() {
        return UserDTO.builder()
                .accountId(this.accountId)
                .name(this.name)
                .phone(this.phone)
                .email(this.email)
                .build();
    }
}
