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
    private TeamAPI teamAPI;

    public static void main(String[] args) {
        new APIServer();
    }

    public APIServer() {
        try {
            serverSocket = new ServerSocket(80);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(this).start();

        teamAPI = new TeamAPI();
    }

    @Override
    public void run() {
        while (true) {
            try {
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
            }
            response = request;
        } else {
            response = "APIs for NBA made by Deworm.";
        }
        return response;
    }
}
