package main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

import service.ServiceFactory;
import service.impl.ServiceFactoryImpl;
import api.APIServer;
import dao.impl.InitDaoImpl;

/**
 * 服务端主入口
 * Created by Vboar on 2015/5/31.
 */
public class Console {


    private boolean rmiServerOn = false;
    private boolean apiServerOn = false;
    
    /**
     * 远程对象
     */
    private Remote reg;
    
    /**
     * 端口
     */
    public static int port;
    
    /**
     * IP地址
     */
    public static String address;

    private APIServer apiServer;
    
    static{
    	Properties prop = new Properties();
    	try{
    		InputStream in = new BufferedInputStream(new FileInputStream("nba.properties"));
    		prop.load(in);
    		address = InetAddress.getLocalHost().getHostAddress();
    		port = 8888;
            System.setProperty("java.rmi.server.hostname", address);
    	}catch(Exception e){
    		System.out.println(e);
    	}
    }

    public Console() {
        apiServer = new APIServer();
    }

    public static void main(String[] args) {

        System.out.println("Commands you can input are as follows (Input the number):");
        System.out.println("1. start rmi server");
        System.out.println("2. stop rmi server");
        System.out.println("3. start api server");
        System.out.println("4. stop api server");
        System.out.println("5. init database");

        Console console = new Console();
        //BufferedReader br = null;
        while (true) {
            try {
                System.out.print("> ");
                String command = new BufferedReader(new InputStreamReader(System.in)).readLine();
                switch (command) {
                    case "1":
                        console.startRmiServer();
                        break;
                    case "2":
                        console.stopRmiServer();
                        break;
                    case "3":
                        console.startApiServer();
                        break;
                    case "4":
                        console.stopApiServer();
                        break;
                    case "5":
                        console.initDatabase();
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startRmiServer() {
        if (rmiServerOn) {
            System.out.println("Rmi server has already been on!");
        } else {
            rmiServerOn = true;
            try {
				reg = LocateRegistry.createRegistry(port);
				ServiceFactory serviceFactory = ServiceFactoryImpl.getInstance();
				Naming.rebind("rmi://" + address + ":" + port + "/ServiceFactory", serviceFactory);
				System.out.println("Rmi server is on now!");
	       } catch (Exception e) {
				e.printStackTrace();
            }
        }
    }

    private void stopRmiServer() {
        if (rmiServerOn) {
        	// 关闭服务器端RMI服务
			try {
				UnicastRemoteObject.unexportObject(reg, true);
			    rmiServerOn = false;
			    System.out.println("Rmi server is off now!");
			} catch (NoSuchObjectException e) {
				e.printStackTrace();
			}
        } else {
            System.out.println("Rmi server has already been off!");
        }
    }

    private void startApiServer() {
        if (apiServerOn) {
            System.out.println("Api server has already been on!");
        } else {
            apiServer.start();
            apiServerOn = true;
            System.out.println("Api server is on now!");
        }
    }

    private void stopApiServer() {
        if (apiServerOn) {
            apiServerOn = false;
            apiServer.stop();
            System.out.println("Api server is off now!");
        } else {
            System.out.println("Api server has already been off!");
        }
    }

    private void initDatabase() {
        InitDaoImpl id = new InitDaoImpl();
        id.initDatabase();
        System.out.println("Init database Successfully!");
    }

}
