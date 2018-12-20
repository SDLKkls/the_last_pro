package com.baizhi.cmm.service;

import com.baizhi.cmm.dto.BannerDto;
import com.baizhi.cmm.entity.Banner;
import com.baizhi.cmm.mapper.BannerMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
@Transactional
@Slf4j
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public BannerDto queryAll(int page, int rows) {
        PageHelper.startPage(page, rows, "id");
        PageInfo<Banner> info = new PageInfo<>(bannerMapper.selectAll());
        return new BannerDto(bannerMapper.selectCount(new Banner()), info.getList());
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerMapper.updateByPrimaryKey(banner);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteBanner(Banner banner, String realPath) {
        Banner banner1 = bannerMapper.selectByPrimaryKey(banner.getId());
        File file = new File(realPath + "/" + banner1.getImgPath());
        if (file.isFile()) {
            file.delete();
        }
        bannerMapper.delete(banner);
    }

    @Override
    public void insertBanner(Banner banner) {
        bannerMapper.insert(banner);
    }
}
