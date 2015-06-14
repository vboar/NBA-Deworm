package ui.util;

/**
 * Created by Vboar on 2015/6/14.
 */
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;

public class LoadFont {

    public static Font loadFont(String fontFileName, int style, float fontSize) {
        try {
            File file = new File("font/" + fontFileName);
            FileInputStream aixing = new FileInputStream(file);
            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
            Font dynamicFontPt = dynamicFont.deriveFont(style, fontSize);
            aixing.close();
            return dynamicFontPt;
        } catch (Exception e)//异常处理
        {
            e.printStackTrace();
            return new java.awt.Font("宋体", Font.PLAIN, 14);
        }
    }

}
