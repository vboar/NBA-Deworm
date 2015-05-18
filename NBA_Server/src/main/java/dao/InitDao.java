package dao;

/**
 * 数据库初始化操作接口
 * 
 * created by JaneLDQ on 2015年5月17日 下午7:34:51
 */
public interface InitDao {
	
    /**
     * 创建所有的新表
     */
    public void createTable();

    /**
     * 删除所有的表
     */
    public void dropTable();

    /**
     * 清空所有的表中的所有数据
     */
    public void truncateTable();
    
    /**
     * 数据入库
     */
    public void fillTable();
}
