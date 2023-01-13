package com.lawencon.bootcamptest.dto.score;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ScoreInsertReqDto {
    public Map<String,Object> mapQuestionPackageDetailIds;
    
    public String questionPackageDetailId;
    public String assignDetailId;
    public Float score;
}
