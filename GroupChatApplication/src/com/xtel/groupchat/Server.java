package com.xtel.groupchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;


public class Server {

    static Vector<Socket> ClientSockets;
    int clientCount = 0;


    Server() throws IOException {
        Date dNow = new Date();
        System.out.println("MultiThreadServer started at " + String.format("%tc", dNow));
        System.out.println();

        ServerSocket server = new ServerSocket(8000);
        ClientSockets = new Vector<Socket>();

        while (true) {
            Socket client = server.accept();
            AcceptClient acceptClient = new AcceptClient(client);
            System.out.println(
                    "Connection from Socket " + "[addr = " + client.getLocalAddress() + ",port = " + client.getPort()
                    + ",localport = " + client.getLocalPort() + "]");

        }
//		 server.close();
    }

    public static void main(String[] args) throws IOException {
		Server server = new Server();
    }

    class AcceptClient extends Thread {

        Socket ClientSocket;
        DataInputStream din;
        DataOutputStream dout;

        AcceptClient(Socket client) throws IOException {
            ClientSocket = client;
            din = new DataInputStream(ClientSocket.getInputStream());
            dout = new DataOutputStream(ClientSocket.getOutputStream());

            clientCount++;
            ClientSockets.add(ClientSocket);

            start();
        }

        public void run() {

            try {
                while (true) {
                    String msgFromClient = din.readUTF();
                    System.out.println(msgFromClient);
                    for (int i = 0; i < ClientSockets.size(); i++) {
                        Socket pSocket = (Socket) ClientSockets.elementAt(i);
                        if (ClientSocket.equals(pSocket)) {
                            continue;
                        }
                        DataOutputStream pOut = new DataOutputStream(pSocket.getOutputStream());
                        pOut.writeUTF(msgFromClient);
                        pOut.flush();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
