package user_interaction;

import maxflow.ResidualNet;
import maxflow.ResidualNetImpl;

import java.io.*;
import java.util.LinkedList;

public final class Shell {
    public Shell() {
    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        convertInputToInt(createInputStream("input.txt"));
        execute(stdin);

    }

    private static void execute(BufferedReader stdin) throws IOException {
        boolean quit = false;
        while (!quit) {
            System.out.println("maxflow> ");
            String input = stdin.readLine();
            String[] tokens = input.trim().split("\\s+");
            ResidualNet resNet;
        if (checkInput(tokens[0])) {
            switch (input.toLowerCase().charAt(0)) {
                case 'n':
                    resNet = new ResidualNetImpl(convertInputToInt(createInputStream("datastr1.net")));
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

    private static void createResNet(LinkedList<int[]> convertedInput) {

    }

    private static LinkedList<String> createInputStream(String filename) throws IOException {
        LinkedList<String> lines = new LinkedList<>();
        File file = new File(filename);
        if (file.exists() && file.isFile()) {
            BufferedReader in = new BufferedReader(new FileReader(file));
            boolean eof = false;
            while (!eof) {
                String input = in.readLine();
                if (input == null) {
                    eof = true;
                } else {
                    lines.add(input.trim());
                }
            }
            in.close();
        }
        return lines;
    }


    private static LinkedList<int[]> convertInputToInt(LinkedList<String> lines) {
        LinkedList<int[]> convertedString = new LinkedList<>();
        for (int i = 0; i < lines.size(); i++) {
            String[] tokens = lines.get(i).split("\\s+");
            // First Line = number of Nodes in the net
            if (i == 0 && tokens.length == 1 && !notANumber(tokens[0])) {
                int[] firstLineNodeSize = {Integer.parseInt(tokens[0])};
                convertedString.add(firstLineNodeSize);
            // every other line: 3 numbers. first: from, second: to, third: capacity.
            } else if (i != 0 && tokens.length == 3 && !notANumber(tokens[0]) && !notANumber(tokens[1]) && !notANumber(tokens[2])) {
                int[] edge = {Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])};
                convertedString.add(edge);
            } else {
                System.out.println(i);
                error("The Input File does not suit the Program! Please check if it fits the convention" +
                        "*first line:*" +
                        "ONE NUMBER " +
                        "*every following line:*" +
                        "THE NUMBERS DIVIDED BY A WHITESPACE");
                convertedString.clear();
                convertedString.add(new int[]{0});
                return convertedString;
            }
        }
        return convertedString;
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
                && string.substring(0, 1).matches("[nfmpdcrshq]")) {
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
