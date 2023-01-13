package com.lawencon.bootcamptest.dto.assign;

import java.util.List;

import com.lawencon.bootcamptest.dto.assigndetail.AssignDetailResDataDto;
import com.lawencon.bootcamptest.dto.user.UserResDataDto;

import lombok.Data;

@Data
public class AssignResDataDto {
    
    private String id;
    private UserResDataDto userCandidate;
    private UserResDataDto userReviewer;
    private List<AssignDetailResDataDto> listAssignDetail;
}
