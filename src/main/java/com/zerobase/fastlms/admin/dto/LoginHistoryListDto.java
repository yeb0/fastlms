package com.zerobase.fastlms.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginHistoryListDto {
    private long totalCnt;
    private List<LoginHistoryDto> list;
}
