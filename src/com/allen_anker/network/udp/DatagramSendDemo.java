package com.allen_anker.network.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DatagramSendDemo {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        DatagramSocket sendSocket = new DatagramSocket();
        DatagramPacket packet;
        while (true) {
            String message = in.nextLine();
            if (!message.equals("quit")) {
                packet = new DatagramPacket(message.getBytes(), message.getBytes().length,
                        InetAddress.getByName("127.0.0.1"), 6666);
                sendSocket.send(packet);
            } else {
                break;
            }
        }
        sendSocket.close();
        in.close();
    }
}
