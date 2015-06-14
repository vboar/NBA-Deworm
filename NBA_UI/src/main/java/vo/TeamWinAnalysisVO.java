package vo;

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * 球队胜场与主客场关系统计分析结果VO
 * 
 * created by JaneLDQ on 2015年6月15日 上午1:01:44
 */
public class TeamWinAnalysisVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * PP图——主场拟合直线斜率
	 */
	public Double home_gradient;
	
	/**
	 * PP图——主场拟合直线截距
	 */
	public Double home_intercept;
	
	/**
	 * PP图——主场拟合直线相关系数
	 */
	public Double home_correlation;
	
	/**
	 * PP图——客场拟合直线斜率
	 */
	public Double guest_gradient;
	
	/**
	 * PP图——客场拟合直线截距
	 */
	public Double guest_intercept;
	
	/**
	 * PP图——客场拟合直线相关系数
	 */
	public Double guest_correlation;
	
	/**
	 * 单总体KS检验——主场D值
	 */
	public Double home_D;
	
	/**
	 * 单总体KS检验——主场p-value
	 */
	public Double home_p;
	
	/**
	 * 单总体KS检验——客场D值
	 */
	public Double guest_D;
	
	/**
	 * 单总体KS检验——客场p-value
	 */
	public Double guest_p;
	
	/**
	 * 双总体KS检验——标准化同分布——D值
	 */
	public Double standardKS_D;
	
	/**
	 * 双总体KS检验——标准化同分布检验——p-value
	 */
	public Double standardKS_p;
	
	/**
	 * 双总体KS检验——原始同分布检验——D值
	 */
	public Double KS_D;
	
	/**
	 * 双总体KS检验——原始同分布检验——p-value
	 */
	public Double KS_p;
	
	/**
	 * 偏度峰度检验——主场偏度
	 */
	public Double home_skewness;

	/**
	 * 偏度峰度检验——主场峰度
	 */
	public Double home_kurtosis;
	
	/**
	 * 偏度峰度检验——客场偏度
	 */
	public Double guest_skewness;

	/**
	 * 偏度峰度检验——客场峰度
	 */
	public Double guest_kurtosis;
	
	/**
	 * 偏度峰度检验——s2+k2 :where s is the z-score returned by skewtest and k is the z-score returned by kurtosistest
	 */
	public Double s2_k2;
	
	/**
	 * 偏度峰度检验——p-value;
	 */
	public Double skew_p;
	
	/**
	 * 配对t检验——t值
	 */
	public Double t;
	
	/**
	 * 配对t检验——p-value
	 */
	public Double t_p;
	
	/**
	 * 主场胜场数均值
	 */
	public Double home_mean;
	
	/**
	 * 主场胜场数标准差
	 */
	public Double home_std;
	
	/**
	 * 客场胜场数均值
	 */
	public Double guest_mean;
	
	/**
	 * 客场胜场数标准差
	 */
	public Double guest_std;
	
	/**
	 * 主场P-P图
	 */
	public ImageIcon home_P_P;
	
	/**
	 * 客场P-P图
	 */
	public ImageIcon guest_P_P;
	
	/**
	 * 空构造
	 */
	public TeamWinAnalysisVO(){}
}
