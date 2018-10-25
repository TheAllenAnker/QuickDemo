package com.allen_anker.io;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class IODemo {

    public static void main(String[] args) {
//        createFileFromText();
//        copyFile();
//        System.out.println(directorySize());
        System.out.println(copyFileDirectory("/Users/barryallen/Desktop/Development/IDEA/QuickDemo/src/com/allen_anker", "/Users/barryallen/Desktop/"));
    }

    public static void createFileFromText() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the text you want to save:");
        String line = null;
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("sample.txt"))) {
            while (!(line = in.nextLine()).equals("quit")) {
                bos.write(line.getBytes());
                bos.write("\n".getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FileNotFound");
        } catch (IOException e) {
            e.printStackTrace();
        }
        in.close();
    }

    public static void copyFile() {
        // Or you can use the new Java 7 feature, try-with-resource
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream("sample.txt"));
            bos = new BufferedOutputStream(new FileOutputStream("sample_copy.txt"));
            int length = 0;
            while ((length = bis.read()) != -1) {
                bos.write(length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int directorySize() {
        String directoryPath;
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter the directory path:");
        File file = null;
        while (true) {
            directoryPath = in.nextLine();
            if (directoryPath.equals("quit")) {
                break;
            }
            try {
                file = new File(directoryPath);
                if (!file.exists()) {

                    throw new FileNotFoundException("File or directory not found.");
                } else {
                    break;
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        in.close();

        return getFileSize(file);
    }

    private static int getFileSize(File file) {
        if (file == null) {
            return 0;
        }
        int size = 0;

        if (file.isDirectory()) {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                if (f.isDirectory()) {
                    size += getFileSize(f);
                } else {
                    size += f.length();
                }
            }
        } else {
            size += file.length();
        }

        return size;
    }

    public static boolean copyFileDirectory(String source, String dest) {
        File sourceDir = new File(source);
        File destDir = new File(dest);
        return copyFileDirectory(sourceDir, destDir);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean copyFileDirectory(File sourceDir, File destDir) {
        // Don't copy directory to a director that belongs to the same directory
        destDir = new File(destDir, sourceDir.getName());
        destDir.mkdir(); // this dir is at the same level of sourceDir
        File[] files = sourceDir.listFiles();
        if (files != null) {
            // sourceDir gets deeper into its children
            for (File file : files) {
                if (file.isDirectory()) {
                    copyFileDirectory(file, destDir);
                } else {
                    try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                         BufferedOutputStream bos =
                                 new BufferedOutputStream(new FileOutputStream(new File(destDir, file.getName())))) {
                        int data;
                        while ((data = bis.read()) != -1) {
                            bos.write(data);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
