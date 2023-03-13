package com.zerobase.fastlms.banner.model;

import com.zerobase.fastlms.admin.model.CommonParam;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BannerParam extends CommonParam {
    Long id; // banner id

    String name;
    String imageUrl;
    String linkUrl;
    String openType;
    int sortValue;
    boolean usingYn;
    LocalDate regDt;
}
