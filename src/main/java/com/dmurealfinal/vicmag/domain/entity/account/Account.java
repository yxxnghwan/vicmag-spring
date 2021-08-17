package com.dmurealfinal.vicmag.domain.entity.account;

import com.dmurealfinal.vicmag.domain.BaseTimeEntity;
import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @OneToOne(mappedBy = "account", cascade = CascadeType.REMOVE)
    private KaKaoAccount kaKaoAccount;

    public Account() {}

    @Builder
    public Account(String accountId, String password, String accountType, LocalDateTime createdDateTime, LocalDateTime modifiedDateTime) {
        this.accountId = accountId;
        this.password = password;
        this.accountType = accountType;
        this.createdDateTime = createdDateTime;
        this.modifiedDateTime = createdDateTime;
    }

    public AccountDTO toDTO() {
        return AccountDTO.builder()
                .accountId(this.accountId)
                .password(this.password)
                .accountType(this.accountType)
                .createdDateTime(this.createdDateTime)
                .modifiedDateTime(this.modifiedDateTime)
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
