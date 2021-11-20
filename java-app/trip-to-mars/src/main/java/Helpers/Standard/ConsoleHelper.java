package Helpers.Standard;

import java.util.Scanner;

public class ConsoleHelper {

    public static void print(Object message) {
        System.out.print(message.toString().trim() + " ");  // Add space at end for formatting
    }

    public static void printLine(String message) {
        System.out.println(message);
    }

    public static void printLine() {
        printLine("");
    }


    public static void printHeader(String headerMessage) {
        printLine(String.format("%70s", headerMessage));
    }

    public static void printSuccessLine(Object message) {
        printLine("[SUCCESS] - " + message + ".\n");
    }

    public static void printErrorLine(Object message) {
        printLine("[ERROR] - " + message + "!\n");
    }

    public static void printInvalidOptionSelected() {
        printErrorLine("Invalid option selected");
    }


    public static String getStringFromConsole() {
        Scanner input = new Scanner(System.in);
        print(">> ");
        return input.nextLine();
    }

    public static String getStringFromConsole(String message) {
        printLine(message);
        return getStringFromConsole();
    }

    public static Integer getIntFromConsole() {
        Scanner input = new Scanner(System.in);
        print(">> ");

        try {
            return input.nextInt();
        } catch (Exception e) {
            printErrorLine("Please make sure that your input is valid");
            return getIntFromConsole();
        }
    }

    public static Integer getIntFromConsole(String message) {
        printLine(message);
        return getIntFromConsole();
    }
}
