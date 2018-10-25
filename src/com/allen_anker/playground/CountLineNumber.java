package com.allen_anker.playground;

import java.io.*;
import java.util.Scanner;

public class CountLineNumber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the path to the file below:");
        String fileName = in.nextLine();
        in.close();
        File file = new File(fileName);
        int totalLine = 0;
        int blankLine = 0;
        try(BufferedReader bf = new BufferedReader(new FileReader(file))) {
            String data = null;
            while ((data = bf.readLine()) != null) {
                totalLine += 1;
                if (data.equals("")) blankLine += 1;
            }
        } catch (IOException e) {
            System.out.println("File not found. Please check the file location.");
            e.printStackTrace();
        }
        System.out.println("Total Lines: " + totalLine + ", Blank Lines: " + blankLine);
    }
}