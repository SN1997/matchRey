package com.rey.matchrey.service;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.rey.matchrey.dto.MatchDataDTO;
import mao.utils.MarkdownUtils;
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

    private static String DOTA2 ="dota";
    private static String LOL = "lol";
    private static String CSGO = "csgo";


    /**
     * desc 获取dota赛事信息
     *
     * @author SN
     * @date 2023/07/22
     */
    public void getDotaMatch() throws Exception {

        String data= HttpUtil.get("https://api.vpgame.com/schedule/schedule/unstart_schedule?gameIp="+DOTA2
                                    +"&maxGameTime="+ (DateUtil.tomorrow().getTime()/1000)+"000"
                                    +"&minGameTime="+ (new Date().getTime()/1000)+"000");
        //赛事名称
        String leagueName = null;
        //赛事logo
        String leagueLogo = null;
        //赛事MD格式
        String leagueMd;
        Object[] append = null;
        Object[][] objs = new Object[0][];

        List<Object> dataList = (List<Object>) JSONUtil.parseObj(JSONUtil.parseObj(data).get("data")).get("all_list");
        for (Object obj : dataList){
            MatchDataDTO matchData = JSONObject.parseObject(JSON.toJSONString(obj), MatchDataDTO.class);
            String dateTime = matchData.getGame_time().substring(0,matchData.getGame_time().length()-3);
            MatchDataDTO.aTeam aTeam = matchData.getLeft_team();
            String aLog = this.teamLogo(aTeam.getLogo(),aTeam.getName());
            MatchDataDTO.bTeam bTeam = matchData.getRight_team();
            String bLog = this.teamLogo(bTeam.getLogo(),bTeam.getName());
            leagueName = matchData.getLeague_name();
            leagueLogo = matchData.getLeague_logo();
            Object[] objects = new Object[]{ DateUtil.date(Long.parseLong(dateTime)*1000),aLog+aTeam.getName(), bLog+bTeam.getName(), "BO"+matchData.getRound()};
            //TODO 数据添加问题
            append=  ArrayUtil.append(objs, objects);
        }
        leagueMd = this.logo(leagueLogo,leagueName);
        String s = this.MDW(leagueMd,leagueName, new Object[][]{append});
        System.out.println(s);

    }


    /**
     * 获取战队图标
     * @param url
     * @param name
     * @return
     */
    public String logo(String url,String name){
        return MarkdownUtils.of().photo(name,url).build();
    }

    public String teamLogo(String name ,String url){
        String s = "<img src=\""+name+"\" alt=\""+url+"\" style=\"zoom:15%;\" />";
        return s;
    }


    public String MDW(String leagueMd,String leagueName,Object[][] datas){
        String[] name = new String[]{"比赛时间", "队伍名称", "队伍名称", "BO"};
        String s = MarkdownUtils.of(). bigTitle(leagueMd+""+leagueName)
                .table().data(name,datas).endTable().build();
        return s;
    }


    //[{"id":110959,"colour":"#A0A0A0","round":3,"category":"csgo","followed":false,"status":"UNSTART","league_id":3650,"league_name":"2023年BLAST Premier:秋季总决赛","league_logo":"https://image.bnedata.com/esporst/20230702/64a102f6e9873.png","league_clickable":true,"match_index":3,"game_time":1690030800000,"bp_ongoing":false,"match_status":"UNKNOWN","left_team":{"id":35136,"name":"EG","logo":"https://image.bnedata.com/data/upload/20200523/5ec89f2a78f24.png","big_score":0,"gold_diff":0},"right_team":{"id":35135,"name":"G2","logo":"https://image.bnedata.com/data/upload/20210425/608515d16e85c.png","big_score":0,"gold_diff":0},"view_count":2,"live_video":false,"live_animation":true}]
}
