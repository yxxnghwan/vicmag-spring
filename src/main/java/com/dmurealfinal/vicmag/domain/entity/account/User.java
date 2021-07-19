package com.dmurealfinal.vicmag.domain.entity.account;

import com.dmurealfinal.vicmag.domain.dto.CompanyDTO;
import com.dmurealfinal.vicmag.domain.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "TB_USER")
@Entity
public class User {
    @Id
    @Column(length = 30)
    private String accountId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Account account;

    @Column(length = 60, nullable = false)
    private String name;

    @Column(length = 30)
    private String phone;

    @Column(length = 150)
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

    public static List<UserDTO> toDTOList (List<User> entityList) {
        List<UserDTO> result = new ArrayList<>();
        for(User entity : entityList) {
            result.add(entity.toDTO());
        }
        return result;
    }
}
