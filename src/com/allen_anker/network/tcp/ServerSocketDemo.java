package com.allen_anker.network.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketDemo {
    public static void main(String[] args) throws IOException {
        uploadTest();
    }

    private static void demo1() throws IOException {
        ServerSocket serverSocket = new ServerSocket(11604);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        os.write("Yes, I DO!".getBytes());
        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
        System.out.println(new String(bytes, 0, len));
        is.close();
        os.close();
        socket.close();
    }

    private static void demo2() throws IOException {
        ServerSocket serverSocket = new ServerSocket(11704);
        Socket socket = serverSocket.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream ps = new PrintStream(socket.getOutputStream());
        String message;
        while ((message = br.readLine()) != null) {
            System.out.println(message);
        }
        ps.println();
        br.close();
        ps.close();
        socket.close();
    }

    private static void test1() throws IOException {
        ServerSocket serverSocket = new ServerSocket(11704);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintStream ps = new PrintStream(socket.getOutputStream());
                    ps.println(new StringBuffer(br.readLine()).reverse().toString());
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void uploadTest() throws IOException {
        ServerSocket serverSocket = new ServerSocket(11701);
        Socket socket = serverSocket.accept();
        BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream ps = new PrintStream(socket.getOutputStream());
        String fileName = bf.readLine();
        File file = new File("upload");
        file.mkdir();
        File newFile = new File(file, fileName);
        if (newFile.exists()) {
            ps.println("existed");
            socket.close();
            return;
        } else {
            ps.println("OK");
        }
        FileOutputStream fos = new FileOutputStream(newFile);
        InputStream is = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int l = 0;
        while ((l = is.read(bytes)) != -1) {
            fos.write(bytes, 0, l);
        }
        fos.close();
        socket.close();
    }
}
