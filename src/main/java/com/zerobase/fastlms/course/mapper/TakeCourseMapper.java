package com.zerobase.fastlms.course.mapper;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.model.TakeCourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeCourseMapper {
    long selectListCount(TakeCourseParam parameter); // 1 페이징 처리는 대부분 이렇게 두 가지로
    List<TakeCourseDto> selectList(TakeCourseParam parameter); // 2 페이징 처리는 대부분 이렇게 두 가지로

    List<TakeCourseDto> selectListMyCourse(TakeCourseParam parameter);
}
