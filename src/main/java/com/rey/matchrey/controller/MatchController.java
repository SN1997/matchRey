package com.rey.matchrey.controller;

import com.rey.matchrey.service.MatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author SN
 * @Date 2023/7/22 11:48
 * @description
 */
@RestController
public class MatchController {

    @Resource
    private MatchService matchService;


    @GetMapping("/rey/macth")
    public String getMacth(String type) throws Exception {
        return matchService.getDotaMatch(type);
    }


}
