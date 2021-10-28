package com.xds.educms.controller;

import com.xds.commonutils.R;
import com.xds.educms.entity.CrmBanner;
import com.xds.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Bai Xu
 * @Date 2021/9/15 9:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;
    //查询所有的banner
    @GetMapping("getAllBanner")
    public R getAllBanner(){
     List<CrmBanner> list= bannerService.selectAllBanner();
     return R.ok().data("list",list);
    }
}
