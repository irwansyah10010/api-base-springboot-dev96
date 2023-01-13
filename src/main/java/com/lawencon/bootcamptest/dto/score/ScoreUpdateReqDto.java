package com.lawencon.bootcamptest.dto.score;

import lombok.Data;

@Data
public class ScoreUpdateReqDto {
    private String id;
    private Float score;

    private Integer version;
}
