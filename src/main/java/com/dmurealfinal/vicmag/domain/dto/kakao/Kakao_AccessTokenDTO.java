package com.dmurealfinal.vicmag.domain.dto.kakao;

import lombok.Data;

public @Data class Kakao_AccessTokenDTO {
    private String accountId;
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private Integer refresh_token_expires_in;
    private String scope;
    private String token_type;
}
