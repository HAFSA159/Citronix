package com.citronix.dto.response;

import lombok.Data;

@Data
public class TreeResponse {
    private Long id;

    public TreeResponse(Long id) {
        this.id = id;
    }
}
