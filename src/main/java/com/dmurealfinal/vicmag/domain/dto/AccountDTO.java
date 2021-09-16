package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Account;
import com.dmurealfinal.vicmag.domain.entity.account.Company;
import com.dmurealfinal.vicmag.domain.entity.account.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
public class AccountDTO {
    private String accountId;

    private String password;

    private String accountType;

    // for response
    private UserDTO user;
    private CompanyDTO company;
    private AdminDTO admin;
    private KakaoAccountDTO kakaoAccount;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime modifiedDateTime;

    public AccountDTO() {}

    @Builder
    public AccountDTO(String accountId, String password, String accountType, UserDTO user, CompanyDTO company, AdminDTO admin, KakaoAccountDTO kakaoAccount, LocalDateTime createdDateTime, LocalDateTime modifiedDateTime) {
        this.accountId = accountId;
        this.password = password;
        this.accountType = accountType;
        this.user = user;
        this.company = company;
        this.admin = admin;
        this.kakaoAccount = kakaoAccount;
        this.createdDateTime = createdDateTime;
        this.modifiedDateTime = modifiedDateTime;
    }

    public Account toEntity() {
        return Account.builder()
                .accountId(this.accountId)
                .password(this.password)
                .accountType(this.accountType)
                .build();
    }
}
