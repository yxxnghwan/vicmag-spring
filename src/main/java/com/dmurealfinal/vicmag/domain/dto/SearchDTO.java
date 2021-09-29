package com.dmurealfinal.vicmag.domain.dto;

import lombok.Data;

public @Data
class SearchDTO {
    private String searchText;
    private String category;
    private Integer boardPage;
    private Integer boardSize;
    private Integer magazinePage;
    private Integer magazineSize;
}
