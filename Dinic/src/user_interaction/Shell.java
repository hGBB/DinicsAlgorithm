package user_interaction;

import maxflow.Net;
import maxflow.NetImpl;
import maxflow.ResidualNet;

import java.io.*;
import java.util.LinkedList;

public final class Shell {
    private Shell() {
    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin
                = new BufferedReader(new InputStreamReader(System.in));
        execute(stdin);

    }

    private static void execute(BufferedReader stdin) throws IOException {
        boolean quit = false;
        Net net = null;
        ResidualNet resNet = null;
        while (!quit) {
            System.out.print("dinic> ");
            String input = stdin.readLine();
            String[] tokens = input.trim().split("\\s+");
            if (checkInput(tokens)) {
                switch (input.toLowerCase().charAt(0)) {
                    case 'n':
                        LinkedList<String> inputFile = createInputStream(tokens[1]);
                        if (inputFile.size() != 0) {
                            net = new NetImpl(convertInputToInt(inputFile));
                        }
                        break;
                    case 'f':
                        LinkedList<int[]> flowInputFile = convertInputToInt(createInputStream(tokens[1]));
                        for (int i = 1; i < flowInputFile.size(); i++) {
                            int[] inp = flowInputFile.get(i);
                            net.getFlow().setEdgeFlow(inp[0], inp[1], inp[2]);
                        }
                        break;
                    case 'm':
                        break;
                    case 'p':
                        break;
                    case 'd':
                        System.out.println(net);
                        break;
                    case 'c':
                        System.out.println(net.getFlow());
                        System.out.println(net.getFlow().isValidFlow());
                        break;
                    case 'r':
                        resNet = net.createResidualNet();
                        System.out.println("Residual net is:");
                        System.out.println(resNet);
                        break;
                    case 's':

                   //     System.out.println(net.isSinkReachableFromSource());
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


    private static LinkedList<String> createInputStream(String filename)
            throws IOException {
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
        } else {
            error("The specified file does not exist!");
        }
        return lines;
    }

    private static LinkedList<int[]>
    convertInputToInt(LinkedList<String> lines) {
        LinkedList<int[]> convertedString = new LinkedList<>();
        for (int i = 0; i < lines.size(); i++) {
            String[] tokens = lines.get(i).split("\\s+");
            // First Line = number of Nodes in the net
            if (i == 0 && tokens.length == 1 && isANumber(tokens[0])) {
                int[] firstLineNodeSize = {Integer.parseInt(tokens[0])};
                convertedString.add(firstLineNodeSize);
                // every other line: 3 numbers.
                // first: source, second: target, third: capacity.
            } else if (i != 0 && tokens.length == 3
                    && isANumber(tokens[0]) && isANumber(tokens[1])
                    && isANumber(tokens[2])) {
                // the array in which the data is stored starts at 1 therefore
                // we need to move all sources / targets 1 to the left
                int[] edge = {Integer.parseInt(tokens[0]) - 1,
                        Integer.parseInt(tokens[1]) - 1,
                        Integer.parseInt(tokens[2])};
                convertedString.add(edge);
            } else {
                error("The Input File does not suit the Program! " +
                        "Please check if it fits the convention" +
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
    private static boolean isANumber(String number) {
        try {
            //noinspection ResultOfMethodCallIgnored
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static boolean checkInput(String[] token) {
        if (token[0].hashCode() != 0) {
            if (token[0].substring(0, 1).matches("[mpdcrshq]")
                    && token.length == 1) {
                return true;
            } else if (token[0].substring(0, 1).matches("[nf]")
                    && token.length == 2) {
                return true;
            }
        }
        error("Input is not correct. Try 'Help'" +
                " for a list of viable commands");
        return false;

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
