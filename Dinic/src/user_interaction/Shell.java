package user_interaction;

import java.io.*;
import java.util.LinkedList;

public final class Shell {
    public Shell() {
    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        createInputStream("input.txt");
        execute(stdin);

    }

    private static void execute(BufferedReader stdin) throws IOException {
        boolean quit = false;
        while (!quit) {
            System.out.println("maxflow> ");
            String input = stdin.readLine();
            String[] tokens = input.trim().split("\\s+");

        if (checkInput(tokens[0])){
            switch (input.toLowerCase().charAt(0)) {
                case 'n':
                    break;
                case 'f':
                    break;
                case 'm':
                    break;
                case 'p':
                    break;
                case 'd':
                    break;
                case 'c':
                    break;
                case 'r':
                    break;
                case 's':
                    break;
                case 'h':
                    break;
                case 'q':
                    quit = true;
                    break;
                default:
                    break;
            }
            }
        }
    }

    public static void createInputStream(String filename) throws IOException {
        int nodes;
        LinkedList<int[]> edges;
        LinkedList<String> lines = new LinkedList<>();
        File file = new File(filename);
        if (file.exists() && file.isFile()) {
            BufferedReader in = new BufferedReader(new FileReader(file));
            boolean eof = false;
            nodes = Integer.parseInt(in.readLine());


            while (!eof) {
                String input = in.readLine();
                if (input == null) {
                    eof = true;
                } else {
                    lines.add(input);
                    System.out.println(input);
                }
            }
            in.close();
        }
    }

    /**
     * Help-method to check if the User input contains
     * numbers for certain calls.
     *
     * @param number A String being checked if it contains solely a number.
     * @return Weather the String can be cast into an Integer.
     */
    private static boolean notANumber(String number) {
        try {
            //noinspection ResultOfMethodCallIgnored
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    private static boolean checkInput(String string) {
        if (string.hashCode() != 0
                && string.substring(0, 1).matches("[nfmpdcrshq]]")) {
            return true;
        } else {
            error("Input is not correct. Try 'Help' for a list of viable commands");
        return false;
        }
    }

        /**
         * Help-method to standardize the Error messages printed on the console.
         *
         * @param msg A message helping the User to prevent incorrect inputs.
         */
        private static void error(String msg) {
            System.out.println("Error! " + msg);
        }
}
