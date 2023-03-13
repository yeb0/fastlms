package com.zerobase.fastlms.banner.model;

import com.zerobase.fastlms.admin.model.CommonParam;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class BannerInput {

    String name;
    String imageUrl;
    String linkUrl;
    String openType;
    int sortValue;
    boolean usingYn;
    LocalDate regDt;
    long bannerId;

    // 삭제를 위한 속성

    String idList;

}
