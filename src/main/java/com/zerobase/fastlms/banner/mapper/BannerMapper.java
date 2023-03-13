package com.zerobase.fastlms.banner.mapper;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    List<BannerDto> select(long pagestart, long pagesize); // 페이징처리하기 위함 count

    List<BannerDto> selectList();

    long selectListCount(BannerParam parameter);

    List<BannerDto> selectListCountNumber(BannerParam parameter);

}
