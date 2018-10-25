package com.allen_anker.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Let's try that send and receive in multi-threads
 */
public class SendReceiveDemo {
    public static void main(String[] args) throws InterruptedException {
        new ReceiveThread().start();
        Thread.sleep(10);
        new SendThread().start();
    }
}

class SendThread extends Thread {
    @Override
    public void run() {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReceiveThread extends Thread {
    @Override
    public void run() {
        try {
            DatagramSocket receiveSocket = new DatagramSocket(6666);
            byte[] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes, 1024);
            while (true) {
                receiveSocket.receive(packet);
                byte[] data = packet.getData();
                int length = packet.getLength();
                System.out.println("From " + packet.getAddress().getHostName() + ": " + new String(data, 0, length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
