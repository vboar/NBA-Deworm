package util;

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
     * 进攻效率(球队高阶)
     */
    OFF_RTG,
    
    /**
     * 防守效率(球队高阶)
     */
    DEF_RTG,
    
    /**
     * 进攻篮板效率(球队高阶)
     */
    ORB_PCT,
    
    /**
     * 防守篮板效率(球队高阶)
     */
    DRB_PCT;
    
    @Override
    public String toString(){
    	return super.toString().toLowerCase();
    }
}
