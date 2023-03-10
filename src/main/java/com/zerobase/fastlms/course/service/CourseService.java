package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;

import java.util.List;

public interface CourseService {
    /**
     * 강의 등록
     */
    boolean add(CourseInput parameter);

    /**
     * 강의목록
     */
    List<CourseDto> list(CourseParam parameter);

    /**
     *강의 상세 정보
     */
    CourseDto getById(long id);

    /**
     * 강의 정보 수정
     */
    boolean set(CourseInput parameter);

    /**
     * 강의 내용 삭제
     */
    boolean del(String idList);
}
