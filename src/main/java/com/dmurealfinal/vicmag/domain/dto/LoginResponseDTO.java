package com.dmurealfinal.vicmag.domain.dto;

import lombok.Data;

public @Data
class LoginResponseDTO {
    private AccountDTO account;
    private String jwt;
}
