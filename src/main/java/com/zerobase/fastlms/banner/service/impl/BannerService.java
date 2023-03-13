package com.zerobase.fastlms.banner.service.impl;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.dto.BannerListDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseParam;

import java.util.List;

public interface BannerService {

    boolean add(BannerInput parameter);

    List<BannerDto> list(); // Banner List

    List<BannerDto> list(BannerParam parameter); // cf) Banner List Count

    BannerListDto list(long pageIndex, long pageSize);

    boolean del(String idList);
}
