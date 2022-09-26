package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class NetConnect {
    public static NetConnect netConnect;
    public String getString;

    private ServerSocket server;
    private static InputStreamReader Sysin,Socin;
    private static BufferedReader SysBuf,SocBuf;
    private static PrintWriter Socout;
    private static Socket socket;

    private NetConnect() {
        connectNet();
    }

    public static NetConnect getInstance(){
        if(netConnect==null){
            netConnect=new NetConnect();
        }
        return netConnect;
    }

    private void connectNet(){
        try{  //建立套接字
            server = new ServerSocket();     //监听
            server.bind(new InetSocketAddress("172.30.122.165",9377));
            socket = server.accept();             //建立连接

            Sysin = new InputStreamReader(System.in);
            SysBuf = new BufferedReader(Sysin);
            Socin = new InputStreamReader(socket.getInputStream());
            SocBuf = new BufferedReader(Socin);
            Socout = new PrintWriter(socket.getOutputStream());

        } catch (Exception e){
            System.out.println("Error:"+e);
        }
    }

    public void LoginJudge() throws IOException {
        //通信
        System.out.println("Client:" + SocBuf.readLine());
        QueryDB queryDB=QueryDB.getInstance();
        boolean isRight=queryDB.LoginApp(SocBuf.readLine());
        if(isRight){
            Socout.println("right");
        }else{
            Socout.println("error");
        }
        Socout.flush();

//        while(!readline.equals("bye")){
//            Socout.println(readline);
//            Socout.flush();
//            System.out.println("Server:" + readline);
//            String rl = SocBuf.readLine();
//            if(!rl.equals("ok"))
//                System.out.println("Client:"+rl);
//            else
//                break;
//            readline = SysBuf.readLine();
//        }
    }

    public void closeNet() throws IOException {
        //关闭IO/socket
        Socout.close();
        Socin.close();
        server.close();
    }
}
