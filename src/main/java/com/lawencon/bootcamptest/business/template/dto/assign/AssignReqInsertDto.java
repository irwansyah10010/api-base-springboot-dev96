package com.lawencon.bootcamptest.business.template.dto.assign;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AssignReqInsertDto {
    @NotNull(message = "Candidate is must not null")
    private String userCandidateId;

    @NotNull(message = "Reviewer id is must not null")
    private String userReviewerId;
    
    private String questionPackageId;
}
