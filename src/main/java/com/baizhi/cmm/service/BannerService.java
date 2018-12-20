package com.baizhi.cmm.service;

import com.baizhi.cmm.dto.BannerDto;
import com.baizhi.cmm.entity.Banner;

public interface BannerService {
    BannerDto queryAll(int page, int rows);

    void updateBanner(Banner banner);

    void deleteBanner(Banner banner, String realPath);

    void insertBanner(Banner banner);
}
