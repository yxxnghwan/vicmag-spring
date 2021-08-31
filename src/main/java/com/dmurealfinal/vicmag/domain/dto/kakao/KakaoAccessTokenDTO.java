package com.dmurealfinal.vicmag.domain.dto.kakao;

import lombok.Data;

public @Data class KakaoAccessTokenDTO {
    private String access_token;
    private Long expires_in;
    private String refresh_token;
    private Long refresh_token_expires_in;
    private String scope;
    private String token_type;
}
