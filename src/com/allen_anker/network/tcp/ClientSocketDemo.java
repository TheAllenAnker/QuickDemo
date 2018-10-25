package com.allen_anker.network.tcp;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.Scanner;

public class ClientSocketDemo {
    public static void main(String[] args) throws IOException {
        uploadTest();
    }

    private static void demo1() throws IOException {
        Socket socket = new Socket("127.0.0.1", 11604);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        os.write("Would you like to be my girlfriend?".getBytes());
        byte[] message = new byte[1024];
        int len = is.read(message);
        System.out.println(new String(message, 0, len));
        is.close();
        os.close();
        socket.close();
    }

    private static void demo2() throws IOException {
        Socket socket = new Socket("127.0.0.1", 11704);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream ps = new PrintStream(socket.getOutputStream());
        System.out.println(br.readLine());
        ps.println("Welcome to New York");
        br.close();
        ps.close();
        socket.close();
    }

    /**
     * Send a string message to server, the server shall return a reversed one of that string.
     * @throws IOException
     */
    private static void test1() throws IOException {
        Socket socket = new Socket("127.0.0.1", 11704);
        Scanner in = new Scanner(System.in);
        String message;
        System.out.println("Please enter the string you want to reverse below:");
        while (!(message = in.nextLine()).equals("quit")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println(message);
            System.out.println(br.readLine());
        }
        in.close();
        socket.close();
    }

    private static void uploadTest() throws IOException {
        Socket socket = new Socket("127.0.0.1", 11701);
        BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream ps = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the file path below:");
        String filePath = in.nextLine();
        in.close();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }
        ps.println(file.getName());
        String result = bf.readLine();
        if (result.equals("existed")) {
            System.out.println("File has already existed.");
        } else {
            FileInputStream fis = new FileInputStream(file);
            int l = 0;
            byte[] bytes = new byte[1024];
            while ((l = fis.read(bytes)) != -1) {
                ps.write(bytes, 0, l);
            }
            fis.close();
        }
        bf.close();
        ps.close();
        socket.close();
    }
}
