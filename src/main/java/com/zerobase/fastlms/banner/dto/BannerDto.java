package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BannerDto {

    Long id;
    String name;
    String imageUrl; // filename;
    String linkUrl; // urlFilename;
    String openType;
    int sortValue;
    boolean usingYn;
    LocalDate regDt;


    long totalCount;
    long seq;

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .name(banner.getName())
                .imageUrl(banner.getImageUrl())
                .linkUrl(banner.getLinkUrl())
                .openType(banner.getOpenType())
                .sortValue(banner.getSortValue())
                .usingYn(banner.isUsingYn())
                .regDt(banner.getRegDt())
                .build();
    }
}
