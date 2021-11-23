package com.dmurealfinal.vicmag.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class MyPurchasedDTO {
    private List<MagazineDTO> magazines;
    private List<MagazineBoardDTO> magazineBoards;
}
