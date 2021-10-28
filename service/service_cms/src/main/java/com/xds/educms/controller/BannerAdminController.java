package com.xds.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xds.commonutils.R;
import com.xds.educms.entity.CrmBanner;
import com.xds.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author baixu
 * @since 2021-09-15
 * 后台banner管理接口
 */
@RestController
@RequestMapping("/educms/banner")
@CrossOrigin
public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    //1.分页查询banner
    @GetMapping("pageBeanner/{page}/{limit}")
    public R pageBanner(@PathVariable("page")long page,
                        @PathVariable("limit")long limit){
        Page<CrmBanner> bannerPage = new Page<>();

        crmBannerService.page(bannerPage, null);
        return R.ok().data("items",bannerPage.getRecords()).data("total",bannerPage.getTotal());

    }
    //添加banner
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return R.ok();
    }
    //获取Banner
    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable("id") String id){
        CrmBanner banner = crmBannerService.getById(id);
        return R.ok().data("banner",banner);
    }
    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner crmBanner){
        crmBannerService.updateById(crmBanner);
        return R.ok();
    }
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R updateById(@PathVariable("id")String id){
        crmBannerService.removeById(id);
        return R.ok();
    }


}

