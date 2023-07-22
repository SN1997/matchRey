package com.rey.matchrey.service;

import com.alibaba.fastjson.JSONObject;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author SN
 * @Date 2023/7/22 11:53
 * @description
 */
@Service
public class MatchService {

    String DOTA2 ="dota";
    String LOL = "lol";
    String CSGO = "csgo";


    /**
     * desc 获取dota赛事信息
     *
     * @author SN
     * @date 2023/07/22
     */
    public void getDotaMatch() throws Exception {
        //https://api.vpgame.com/schedule/schedule/unstart_schedule?gameIp=dota&maxGameTime=1690085458819000&minGameTime=1689999073792000
        String data= HttpUtil.get("https://api.vpgame.com/schedule/schedule/unstart_schedule?gameIp="+LOL
                                    +"&maxGameTime="+ (DateUtil.tomorrow().getTime()/1000)+"000"
                                    +"&minGameTime="+ (new Date().getTime()/1000)+"000");
        //RespDto respDto = JSONObject.parseObject(response, RespDto.class);

        List<Object> dataList = (List<Object>) JSONUtil.parseObj(JSONUtil.parseObj(data).get("data")).get("all_list");
        System.out.println(dataList);
    }

    //[{"id":110959,"colour":"#A0A0A0","round":3,"category":"csgo","followed":false,"status":"UNSTART","league_id":3650,"league_name":"2023年BLAST Premier:秋季总决赛","league_logo":"https://image.bnedata.com/esporst/20230702/64a102f6e9873.png","league_clickable":true,"match_index":3,"game_time":1690030800000,"bp_ongoing":false,"match_status":"UNKNOWN","left_team":{"id":35136,"name":"EG","logo":"https://image.bnedata.com/data/upload/20200523/5ec89f2a78f24.png","big_score":0,"gold_diff":0},"right_team":{"id":35135,"name":"G2","logo":"https://image.bnedata.com/data/upload/20210425/608515d16e85c.png","big_score":0,"gold_diff":0},"view_count":2,"live_video":false,"live_animation":true}]
}
