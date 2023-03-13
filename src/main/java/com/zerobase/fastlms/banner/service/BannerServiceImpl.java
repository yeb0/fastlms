package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.dto.BannerListDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.mapper.BannerMapper;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import com.zerobase.fastlms.banner.service.impl.BannerService;
import com.zerobase.fastlms.course.dto.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;

    private final BannerMapper bannerMapper;

    @Override
    public boolean add(BannerInput parameter) {

        Banner banner = Banner.builder()
                .bannerId(parameter.getBannerId())
                .name(parameter.getName())
                .imageUrl(parameter.getImageUrl())
                .linkUrl(parameter.getLinkUrl())
                .openType(parameter.getOpenType())
                .sortValue(parameter.getSortValue())
                .usingYn(parameter.isUsingYn())
                .regDt(parameter.getRegDt())
                .build();

        bannerRepository.save(banner);

        return true;
    }

    @Override
    public List<BannerDto> list() {
        return bannerMapper.selectList();
    }

    @Override
    public List<BannerDto> list(BannerParam parameter) {
        long totalCount = bannerMapper.selectListCount(parameter);
        List<BannerDto> list = bannerMapper.selectListCountNumber(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;
    }


    @Override
    public BannerListDto list(long pageIndex, long pageSize) {

        long totalCount = bannerRepository.count(); // CrudRepository cnt https://jobc.tistory.com/120 메서드 참고

        List<BannerDto> Blist;
        if(totalCount > 0) {
            Blist = bannerMapper.select((pageIndex - 1) * pageSize, pageSize); // 페이징 처리하기 위한 select
            for(int i = 0; i < Blist.size(); ++i) {
                BannerDto bto = Blist.get(i);
                bto.setSeq(totalCount - ((pageIndex - 1) * pageSize + i));
            }
        } else {
            Blist = new ArrayList<>();
        }

        return new BannerListDto(totalCount, Blist);
    }

    @Override
    public boolean del(String idList) {
        if (idList != null && idList.length() > 0) {

            String[] ids = idList.split(",");
            for(String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }
        return true;
    }
}
