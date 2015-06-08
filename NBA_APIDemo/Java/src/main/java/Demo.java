import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Vboar on 2015/6/8.
 */
public class Demo {

    public static void main(String[] args) {
        String content = "";
        try {
            URL url = new URL("http://localhost/team?abbr=HOU");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.connect();
            InputStream inputStream = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            content = br.readLine();
            con.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject(content);
        String abbr = jo.get("abbr").toString();
        JSONObject info = (JSONObject)jo.get("info");
        String name = info.get("name").toString();
        String location = info.get("location").toString();
        String league = info.get("league").toString();
        String division = info.get("division").toString();
        String buildup_time = info.get("buildup_time").toString();
        String record = info.get("record").toString();
        String playeroff_appearance = info.get("playeroff_appearance").toString();
        String championships = info.get("championships").toString();

        System.out.println("球队：" + abbr);
        System.out.println("球队名：" + name);
        System.out.println("位置：" + location);
        System.out.println("联盟：" + league);
        System.out.println("分区：" + division);
        System.out.println("成立时间：" + buildup_time);
        System.out.println("历史：" + record);
        System.out.println("参加季后赛次数：" + playeroff_appearance);
        System.out.println("获得冠军次数：" + championships);
    }

}
