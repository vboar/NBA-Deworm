package vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 直播比赛动态信息类
 * Created by Vboar on 2015/6/8.
 */
public class LiveMatchVO {

    /**
     * id
     */
    public String id;

    /**
     * 耗时
     */
    public String time;

    /**
     * 上座
     */
    public String attendance;

    /**
     * 球馆
     */
    public String gym;

    /**
     * 剩余时间
     */
    public String residualTime;

    /**
     * 主队得分（总分为最后一个）
     */
    public List<String> scoresA = new ArrayList<>();

    /**
     * 客队得分（总分为最后一个）
     */
    public List<String> scoresB = new ArrayList<>();

}
