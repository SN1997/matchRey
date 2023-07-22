package com.rey.matchrey.dto;

/**
 * @Author SN
 * @Date 2023/7/22 11:52
 * @description
 */
public class MatchDataDTO {

    private String league_name;

    private String league_logo;

    private String round;

    private String game_time;

    public aTeam left_team;

    public bTeam right_team;

    public static class aTeam{
        private String id;

        private String name;

        private String logo;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }

    public static class bTeam{
        private String id;

        private String name;

        private String logo;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }


    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public aTeam getLeft_team() {
        return left_team;
    }

    public void setLeft_team(aTeam left_team) {
        this.left_team = left_team;
    }

    public bTeam getRight_team() {
        return right_team;
    }

    public void setRight_team(bTeam right_team) {
        this.right_team = right_team;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public String getLeague_logo() {
        return league_logo;
    }

    public void setLeague_logo(String league_logo) {
        this.league_logo = league_logo;
    }

    public String getGame_time() {
        return game_time;
    }

    public void setGame_time(String game_time) {
        this.game_time = game_time;
    }
}
