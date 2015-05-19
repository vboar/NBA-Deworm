package ui.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SystemConfig {
private static  FrameConfig HOME_CONFIG = null; 

	
static {
	try {
            //创建XML读取器
			SAXReader reader = new SAXReader();
			// 读取XML文件
			Document doc = reader.read("cfg.xml");
			// 获得XML文件根节点
			Element nba = doc.getRootElement();
			// 创建主界面配置对象
			HOME_CONFIG = new FrameConfig(nba.element("homeframe"));
			
			
	}catch (DocumentException e) {
		e.printStackTrace();
	}
}


public static FrameConfig getHOME_CONFIG() {
	return HOME_CONFIG;
}




}
