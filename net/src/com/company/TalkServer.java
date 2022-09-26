package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TalkServer {

    private static Map<String, Socket> clientList = new HashMap();
    private static ServerThread serverThread = null;

    public static ServerThread startServer() {
        System.out.println("run the service");
        if (serverThread != null) {
            showDown();
        }

        serverThread = new ServerThread();
        (new Thread(serverThread)).start();
        System.out.println("success to open the server");
        return serverThread;
    }

    public static void showDown() {
        Iterator var0 = clientList.values().iterator();

        while(var0.hasNext()) {
            Socket socket = (Socket)var0.next();

            try {
                socket.close();
            } catch (IOException var3) {
                var3.printStackTrace();
            }
        }

        serverThread.Stop();
        clientList.clear();
    }

    public static boolean sendMsgAll(String msg) {
        try {
            Iterator var1 = clientList.values().iterator();

            while(var1.hasNext()) {
                Socket socket = (Socket)var1.next();
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(msg.getBytes("utf-8"));
            }

            return true;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    private static class ServerThread implements Runnable {
        private int port = 9377;
        private String ip="172.30.122.165";
        private boolean isExit = false;
        private ServerSocket server;

        public ServerThread() {
            try {
                this.server = new ServerSocket();
                server.bind(new InetSocketAddress("172.30.122.165",9377));
                System.out.println("success to run the server,theport:" + this.port);
            } catch (IOException var2) {
                System.out.println("fail to run the server,the result is：" + var2.getMessage());
            }

        }

        public void run() {
            while(true) {
                try {
                    if (!this.isExit) {
                        System.out.println("waiting the connect with phone... ... ");
                        final Socket socket = this.server.accept();
                        final String address = socket.getRemoteSocketAddress().toString();
                        System.out.println("connect success,the phone is" + address);
                        (new Thread(new Runnable() {
                            public void run() {
                                boolean var16 = false;

                                label120: {
                                    try {
                                        var16 = true;
                                        synchronized(this) {
                                            clientList.put(address, socket);
                                        }

                                        InputStream inputStream = socket.getInputStream();
                                        byte[] buffer = new byte[1024];

                                        int len;
                                        while((len = inputStream.read(buffer)) != -1) {
                                            String text = new String(buffer, 0, len);
                                            System.out.println("receive the data is：" + text);
                                            sendMsgAll(text);
                                        }

                                        var16 = false;
                                        break label120;
                                    } catch (Exception var21) {
                                        System.out.println("the error is：" + var21.getMessage());
                                        var16 = false;
                                    } finally {
                                        if (var16) {
                                            synchronized(this) {
                                                System.out.println("close the link：" + address);
                                                clientList.remove(address);
                                            }
                                        }
                                    }

                                    synchronized(this) {
                                        System.out.println("close the link：" + address);
                                        clientList.remove(address);
                                        return;
                                    }
                                }

                                synchronized(this) {
                                    System.out.println("close the link：" + address);
                                    clientList.remove(address);
                                }

                            }
                        })).start();
                        continue;
                    }
                } catch (IOException var3) {
                    var3.printStackTrace();
                }

                return;
            }
        }

        public void Stop() {
            this.isExit = true;
            if (this.server != null) {
                try {
                    this.server.close();
                    System.out.println("close the server");
                } catch (IOException var2) {
                    var2.printStackTrace();
                }
            }

        }
    }
}
