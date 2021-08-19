package com.dmurealfinal.vicmag.domain.dto;

import com.dmurealfinal.vicmag.domain.entity.account.Account;
import com.dmurealfinal.vicmag.domain.entity.account.KaKaoAccount;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KakaoAccountDTO {

    private String accountId;

    private Long kakaoId;

    private String accessToken;

    private String refreshToken;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime expiresIn;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime refreshTokenExpiresIn;

    private AccountDTO account;

    public KakaoAccountDTO() {}

    @Builder
    public KakaoAccountDTO(String accountId, Long kakaoId, String accessToken, String refreshToken, LocalDateTime expiresIn, LocalDateTime refreshTokenExpiresIn,AccountDTO account) {
        this.accountId = accountId;
        this.kakaoId = kakaoId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
        this.account = account;
    }

    public KaKaoAccount toEntity() {
        return KaKaoAccount.builder()
                .accountId(this.accountId)
                .kakaoId(this.kakaoId)
                .accessToken(this.accessToken)
                .refreshToken(this.refreshToken)
                .expiresIn(this.expiresIn)
                .refreshTokenExpiresIn(this.refreshTokenExpiresIn)
                .build();

    }
}
