package com.zxh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.zxh.common.Result;
import com.zxh.site.entity.Site;
import com.zxh.site.repository.SiteRepository;
import com.zxh.site.service.ISiteService;
import com.zxh.site.vo.SiteVo;
import com.zxh.util.BeanCopyUtil;
import com.zxh.util.DateUtils;
import com.zxh.util.ThreadPoolExecutorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description:
 * @Author: zhengxinhui
 * @Date: 2021/7/20 15:34
 */
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/")
public class SiteController {

    @Autowired
    private final ISiteService siteService;
    @Autowired
    private final SiteRepository siteRepository;


    @RequestMapping(value = "/querySites")
    public Result<List<SiteVo>> querySites() {
        StopWatch stopWatch = new StopWatch("querySites" + DateUtils.getCurrentFormatDate());
        stopWatch.start("查询结果");
        HashMap hashMap = new HashMap();
        hashMap.put("lat", "30.783537");
        hashMap.put("lng", "106.294867");
        PageInfo<SiteVo> pageInfo = siteService.querySites(1, 5000, hashMap);
        //siteRepository.pageList(1,100,lambdaQuery);
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        return new Result<>(pageInfo.getList());
    }

    @RequestMapping(value = "/querySitesByMuliThread")
    public Result<List<SiteVo>> querySitesByMuliThread() {
        StopWatch stopWatch = new StopWatch("querySites" + DateUtils.getCurrentFormatDate());
        stopWatch.start("多线程计算结果");
        List<SiteVo> siteVoList=summaryDistance();
        siteVoList.stream().sorted(Comparator.comparing(SiteVo::getDistance));
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        return new Result<>(siteVoList);
    }

    private List<SiteVo>  summaryDistance() {
        long start = System.currentTimeMillis();
        int orderStartIndex = 0;
        int querySize = 2000;
        List<Future> sumFuture = new ArrayList<>();
        List<SiteVo> allSites = new ArrayList<>();
        int i=1;
        while (true) {
            long startInWhile = System.currentTimeMillis();
            LambdaQueryWrapper<Site> lambdaQuery = new LambdaQueryWrapper<Site>();
            PageInfo<Site> pageInfo = siteRepository.pageList(i, querySize, lambdaQuery);
            List<SiteVo> siteVos = BeanCopyUtil.copyListProperties(pageInfo.getList(), SiteVo::new);
            allSites.addAll(siteVos);
            long timeUsedOnePage = System.currentTimeMillis() - startInWhile;
            orderStartIndex += querySize;
            if (!CollectionUtils.isEmpty(siteVos)) {
                Future taskFuture = ThreadPoolExecutorUtil.getThreadPoolExecutor().submit(() -> calculateDistance(siteVos));
                sumFuture.add(taskFuture);
            }
            log.info("pageInfo.getList().size():{}",pageInfo.getList().size());
            if (pageInfo.getList().size() < querySize) {
                break;
            }
            i++;
        }
        long timeUsed = System.currentTimeMillis() - start;
        sumFuture.forEach(x->{
            try {
                x.get();
                log.info("阻塞了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        return allSites;
    }

    public void calculateDistance(List<SiteVo> siteList) {
        double lat=30.783537, lng=106.294867;
        siteList.stream().forEach(x->{
            x.setDistance(getDistance(x.getLat(),x.getLng(),lat,lng));
        });

    }

    private double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public  double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        double EARTH_RADIUS = 6378.137;//地球半径
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
}
