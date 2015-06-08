package vo;


/**
 * 直播比赛信息VO类
 * Created by Vboar on 2015/6/1.
 */
public class LiveMatchInfoVO {

    public String id;

    /**
     * 日期
     */
    public String date;

    /**
     * 星期几
     */
    public String day;

    /**
     * 时间
     */
    public String time;

    /**
     * 常规赛/季后赛
     */
    public String matchType;

    /**
     * 主队
     */
    public String homeTeam;

    /**
     * 客队
     */
    public String guestTeam;

    /**
     * 比赛状态
     */
    public String state;

    public LiveMatchInfoVO() {}

}
