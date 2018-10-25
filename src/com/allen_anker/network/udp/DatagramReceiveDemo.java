package com.allen_anker.network.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramReceiveDemo {
    public static void main(String[] args) throws Exception {
        DatagramSocket receiveSocket = new DatagramSocket(6666);
        byte[] bytes = new byte[1024];
        DatagramPacket packet = new DatagramPacket(bytes, 1024);
        while (true) {
            receiveSocket.receive(packet);
            byte[] data = packet.getData();
            int length = packet.getLength();
            System.out.println("From " + packet.getAddress().getHostName() + ": " + new String(data, 0, length));
        }
    }
}
