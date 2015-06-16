package vo;

import java.io.Serializable;

/**
 * 多元回归数据VO
 * 
 * created by JaneLDQ on 2015年6月15日 上午5:38:03
 */
public class MultiRegressionVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 偏差平方和
	 */
	public double q;
	
	/**
	 * 平均标准差
	 */
	public double s;
	
	/**
	 * 复相关系数
	 */
	public double r;
	
	/**
	 * 回归平方和
	 */
	public double u;
	
	/**
	 * m个偏相关系数
	 */
	public double[] v;
	
	/**
	 * F检验值
	 */
	public double f;

}
