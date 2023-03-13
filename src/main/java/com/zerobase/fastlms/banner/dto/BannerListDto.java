package com.zerobase.fastlms.banner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerListDto {
    private long totalCnt;
    private List<BannerDto> list;
}
