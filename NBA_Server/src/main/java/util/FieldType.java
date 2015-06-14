package util;

public enum FieldType {
	
	// 得分
    PTS,     
    //篮板
	TRB, 
	//进攻篮板
	ORB,
	//防守篮板
	DRB,
	//助攻
	AST,
	//盖帽
	BLK, 
	//抢断
	STL, 
	//失误
	TOV,
	//犯规
	PF,
	//三分命中率
	FG3_PCT,
	//投篮命中率
	FGA_PCT,
	//罚球命中率
	FT_PCT,
	//效率（球员高阶）
	PER,
	//真实命中率
	TS_PCT,
	//抢断率
	STL_PCT,
	//盖帽率
	BLK_PCT,
	//助攻率
	AST_PCT,
	//失误率
	TOV_PCT,
	//使用率（球员高阶）
	USG_PCT,    
	//篮板率
	TRB_PCT,
    //进攻篮板效率(球员球队高阶)
    ORB_PCT,
    //防守篮板效率(球员球队高阶)
    DRB_PCT,
    //进攻效率(球队高阶)
    OFF_RTG,
    //防守效率(球队高阶)
    DEF_RTG,
    //进攻-有效命中率
    OFF_EFG_PCT,
    //进攻-失误率
    OFF_TOV_PCT,
    //防守-有效命中率
    OPP_EFG_PCT,
    //防守-失误率
    OPP_TOV_PCT,
    //SOS
    SOS,
    //SRS
    SRS,
    //MOV
    MOV;
    
    @Override
    public String toString(){
    	return super.toString().toLowerCase();
    }
    
    private static FieldType[] types = {
     PTS,TRB, ORB, DRB, AST, BLK, STL, TOV, PF, FG3_PCT, FGA_PCT, FT_PCT,
	 PER, TS_PCT, STL_PCT, BLK_PCT, AST_PCT, TOV_PCT, USG_PCT, TRB_PCT,
	 ORB_PCT, DRB_PCT, OFF_RTG, DEF_RTG, OFF_EFG_PCT, OFF_TOV_PCT, OPP_EFG_PCT,
	 OPP_TOV_PCT, SOS, SRS, MOV
    };
    
    public static int typeToInt(FieldType type){
    	for(int i=0; i<types.length; ++i){
    		if(types[i].equals(type))
    			return i;
    	}
    	return -1;
    }
    
    public static FieldType intToType(int i){
    	if(i<0||i>=types.length)
    		return null;
    	return types[i];
    }
}
