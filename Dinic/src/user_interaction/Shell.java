package user_interaction;

import maxflow.*;

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
        NiveauGraph niveauGraph = null;
        MaxFlow maxFlow = new MaxFlowImpl();
        while (!quit) {
            System.out.print("dinic> ");
            String input = stdin.readLine();
            String[] tokens = input.trim().split("\\s+");
            if (checkInput(tokens)) {
                switch (input.toLowerCase().charAt(0)) {
                    case 'n':
                        LinkedList<int[]> inputFile = convertToArray(
                                createInputList(tokens[1]));
                        if (inputFile.size() != 0
                                && checkForValidNumbers(inputFile)) {
                            net = new NetImpl(inputFile);
                        }
                        break;
                    case 'f':
                        if (net != null) {
                            LinkedList<int[]> flowInputFile =
                                    convertToArray(createInputList(tokens[1]));
                            if (checkForValidNumbers(flowInputFile)) {
                                if (net.getFlow() != null) {
                                    net.getFlow().clear();
                                }
                                for (int i = 1; i < flowInputFile.size(); i++) {
                                    int[] inp = flowInputFile.get(i);
                                    net.getFlow().setEdgeFlow(inp[0],
                                            inp[1], inp[2]);
                                }
                            }
                        }
                        break;
                    case 'm':
                        if (net != null) {
                            maxFlow.computeMaxFlow(net);
                            System.out.println("Maximum flow is: "
                                    + net.getFlow().getTotalFlow());
                        } else {
                            error("ladida");
                        }
                        break;
                    case 'p':
                        if (net != null) {
                            maxFlow.computeMaxFlow(net);
                            System.out.println("Maximum flow is: "
                                    + net.getFlow().getTotalFlow());
                            System.out.println(net.getFlow());
                        } else {
                            error("Initialize a net first!");
                        }
                        break;
                    case 'd':
                        System.out.println(net);
                        break;
                    case 'c':
                        System.out.println(net.getFlow());
                        break;
                    case 'r':
                        resNet = net.createResidualNet();
                        System.out.println("Residual net is:");
                        System.out.println(resNet);
                        break;
                    case 's':
                        if (resNet != null) {
                            niveauGraph = net.createNiveauGraph(resNet);
                            System.out.println("Niveau graph is:\n"
                                    + niveauGraph);
                        }
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


    private static LinkedList<String> createInputList(String filename)
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
    convertToArray(LinkedList<String> lines) {
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
                error("The Input File does not suit the Program! "
                        + "Please check if it fits the convention"
                        + "*first line:*"
                        + "ONE NUMBER "
                        + "*every following line:*"
                        + "THE NUMBERS DIVIDED BY A WHITESPACE");
                convertedString.clear();
                convertedString.add(new int[]{0});
                return convertedString;
            }
        }
        return convertedString;
    }

    private static boolean checkForValidNumbers(LinkedList<int[]> input) {
        // check if all Edges given by the input file are in range of the nodes
        int stayInRange = input.get(0)[0];
        for (int i = 1; i < input.size(); i++) {
            int source = input.get(i)[0];
            int target = input.get(i)[1];
            int capacity = input.get(i)[2];

            if (source < 0 || source > stayInRange || target < 0
                    || target > stayInRange || capacity < 0) {
                error("The input file is broken.");
                return false;
            }
        }


        return true;
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
