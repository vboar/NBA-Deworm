package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.impl.InitDaoImpl;

/**
 * 服务端主入口
 * Created by Vboar on 2015/5/31.
 */
public class Console {


    private boolean rmiServerOn = false;
    private boolean apiServerOn = false;

    public static void main(String[] args) {

        System.out.println("Commands you can input are as follows (Input the number):");
        System.out.println("1. start rmi server");
        System.out.println("2. stop rmi server");
        System.out.println("3. start api server");
        System.out.println("4. stop api server");
        System.out.println("5. init database");

        Console console = new Console();
        BufferedReader br = null;
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
            System.out.println("Rmi server is on now!");
        }
    }

    private void stopRmiServer() {
        if (rmiServerOn) {
            rmiServerOn = false;
            System.out.println("Rmi server is off now!");
        } else {
            System.out.println("Rmi server has already been off!");
        }
    }

    private void startApiServer() {
        if (apiServerOn) {
            System.out.println("Api server has already been on!");
        } else {
            apiServerOn = true;
            System.out.println("Api server is on now!");
        }
    }

    private void stopApiServer() {
        if (apiServerOn) {
            apiServerOn = false;
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
