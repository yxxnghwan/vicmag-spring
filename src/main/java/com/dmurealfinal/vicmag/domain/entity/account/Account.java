package com.dmurealfinal.vicmag.domain.entity.account;

import com.dmurealfinal.vicmag.domain.BaseTimeEntity;
import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "TB_ACCOUNT")
@Entity
public class Account extends BaseTimeEntity {

    @Id
    @Column(length = 30)
    private String accountId;

    @Column(length = 300)
    private String password;

    @Column(length = 10)
    private String accountType;

    @OneToOne(mappedBy = "account", cascade = CascadeType.REMOVE)
    private Company company;

    @OneToOne(mappedBy = "account", cascade = CascadeType.REMOVE)
    private User user;

    public Account() {}

    @Builder
    public Account(String accountId, String password, String accountType) {
        this.accountId = accountId;
        this.password = password;
        this.accountType = accountType;
    }

    public AccountDTO toDTO() {
        return AccountDTO.builder()
                .accountId(this.accountId)
                .password(this.password)
                .accountType(this.accountType)
                .build();
    }

    public static List<AccountDTO> toDTOList (List<Account> entityList) {
        List<AccountDTO> result = new ArrayList<>();
        for(Account entity : entityList) {
            result.add(entity.toDTO());
        }
        return result;
    }
}
