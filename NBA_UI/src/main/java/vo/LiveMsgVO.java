package vo;

/**
 * 比赛消息VO
 * Created by Vboar on 2015/6/1.
 */
public class LiveMsgVO {

    /**
     * 消息类型：0为暂停和结束消息（加粗），1为普通消息，2为强调消息（加粗）
     */
    public int type;

    /**
     * sid
     */
    public String sid;

    /**
     * 内容
     */
    public String content;

    /**
     * 当节剩余时间
     */
    public String residualTime;

    /**
     * 相关内容的球队
     */
    public String team;

    /**
     * 相关内容的得分
     */
    public String scores;

    public LiveMsgVO() {}

}
