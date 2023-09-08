package main.ui;

import main.Config;

import java.util.Scanner;

public class UI {
    private static final Scanner reader = new Scanner(System.in);

    public static void notice(String message) throws InterruptedException {
        println("[Notice] " + message, Config.NOTICE_DISPLAY_DELAY);
    }

    public static void println(String message, int delay) throws InterruptedException {
        System.out.println(message);
        Thread.sleep(delay);
    }

    public static void println(String message) throws InterruptedException {
        println(message, Config.DISPLAY_THROTTLE);
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void newline() throws InterruptedException {
        println("", 0);
    }

    public static String getInput(String prompt) {
        print(prompt + " ");
        return reader.nextLine();
    }
}
