package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * API Server
 * Created by Vboar on 2015/5/31.
 */
public class APIServer implements Runnable {

    private ServerSocket serverSocket;
    private Thread thread;
    private boolean run;
    private TeamAPI teamAPI;
    private PlayerAPI playerAPI;
    public static final String NOTFOUND = "Not found.";
    public static final String NOTSUPPORT = "Not support.";
    public static final String ERROR = "Error.";

    public static void main(String[] args) {
        new APIServer().start();
    }

    public APIServer() {
        teamAPI = new TeamAPI();
        playerAPI = new PlayerAPI();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(80);
        } catch (IOException e) {
            e.printStackTrace();
        }
        run = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        run = false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!run) {
                    serverSocket.close();
                    break;
                }
                Socket socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String str = br.readLine();
                if (str != null && !str.contains("favicon.ico")) {
                    OutputStream os = socket.getOutputStream();
                    PrintWriter pw = new PrintWriter(os);
                    pw.println(handleGet(str));
                    pw.flush();
                    pw.close();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String handleGet(String str) {
        String request = str.split(" ")[1];
        String response = null;
        if (request.length() > 1) {
            if (request.startsWith("/team")) {
                return teamAPI.getStr(request.substring(5));
            } else if (request.startsWith("/player")) {
                return playerAPI.getStr(request.substring(7));
            } else if (request.startsWith("/match")) {
                return playerAPI.getStr(request.substring(6));
            }
        } else {
            response = "APIs for NBA made by Deworm.";
        }
        return NOTSUPPORT;
    }
}
