package util;

/**
 * 热门属性枚举类
 * 
 * created by JaneLDQ on 2015年6月5日 下午8:08:10
 */
public enum FieldType {
	/**
	 * 得分
	 */
    PTS, 
    
    /**
     * 篮板
     */
	TRB, 
	
	/**
	 * 助攻
	 */
	AST,
	
	/**
	 * 盖帽
	 */
	BLK, 
	
	/**
	 * 抢断
	 */
	STL, 
	
	/**
	 * 三分命中率
	 */
	FG3_PCT,
	
	/**
	 * 投篮命中率
	 */
	FGA_PCT,
	
	/**
	 * 罚球命中率
	 */
	FT_PCT,
    
    /**
     * 进攻效率
     */
    OFF_RTG,
    
    /**
     * 防守效率
     */
    DEF_RTG,
    
    /**
     * 进攻篮板效率
     */
    ORB_PCT,
    
    /**
     * 防守篮板效率
     */
    DRB_PCT;
    @Override
    public String toString(){
    	return this.toString().toLowerCase();
    }
}
