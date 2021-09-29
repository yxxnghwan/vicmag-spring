package com.dmurealfinal.vicmag.domain.dto;

import lombok.Data;

import java.util.List;

public @Data
class SearchResultDTO {
    private List<MagazineBoardDTO> magazineBoards;
    private List<MagazineDTO> magazines;
}
