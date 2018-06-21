package user_interaction;


import maxflow.MaxFlow;
import maxflow.MaxFlowImpl;
import maxflow.Net;
import maxflow.NetImpl;
import maxflow.NiveauGraph;
import maxflow.ResidualNet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

/**
 * This class is handles all the user interaction with the program and calls.
 */
public final class Shell {
    private static final int ONE_WORD_COMMAND = 1;
    private static final int TWO_WORD_COMMAND = 2;

    /**
     * Standard constructor
     */
    private Shell() {
    }

    /**
     * Main method computes the user input and calls the business logic layer.
     *
     * @param args Takes arguments.
     * @throws IOException If used in an unintended way.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader stdin
                = new BufferedReader(new InputStreamReader(System.in));
        execute(stdin);
    }

    /**
     * The core method of the shell. Handles the processing of the user input
     * and calls the methods of the program logic.
     *
     * @param stdin The user input.
     * @throws IOException If used in an unintended way.
     */
    private static void execute(BufferedReader stdin) throws IOException {
        boolean quit = false;
        Net net = null;
        ResidualNet resNet = null;
        NiveauGraph niveauGraph;
        MaxFlow maxFlow = new MaxFlowImpl();
        while (!quit) {
            System.out.print("dinic> ");
            String input = stdin.readLine();
            String[] tokens = splitCommand(input);
            if (checkInput(tokens)) {
                switch (tokens[0].charAt(0)) {
                    case 'n':
                        LinkedList<int[]> inputFile = convertToArray(
                                createInputList(tokens[1]));
                        if (inputFile.size() != 0
                                && checkForValidNumbers(inputFile)) {
                            net = new NetImpl(inputFile);
                        }
                        break;
                    case 'f':
                        if (initialized(net)) {
                            LinkedList<int[]> flowInputFile =
                                    convertToArray(createInputList(tokens[1]));
                            if (flowInputFile.size() != 0
                                    && checkForValidNumbers(flowInputFile)) {
                                if (net.getFlow() != null) {
                                    net.getFlow().clear();
                                }
                                for (int i = 1; i < flowInputFile.size(); i++) {
                                    int[] inp = flowInputFile.get(i);
                                    net.getFlow().setEdgeFlow(inp[0],
                                            inp[1], inp[2]);
                                }
                                if (!net.getFlow().isValidFlow()) {
                                    net.getFlow().clear();
                                    error("The given flow is not valid!");
                                }
                            }
                        }
                        break;
                    case 'm':
                        if (initialized(net)) {
                            maxFlow.computeMaxFlow(net);
                            System.out.println("Maximum flow is: "
                                    + net.getFlow().getTotalFlow());
                        }
                        break;
                    case 'p':
                        if (initialized(net)) {
                            maxFlow.computeMaxFlow(net);
                            System.out.println("Maximum flow is: "
                                    + net.getFlow().getTotalFlow());
                            System.out.println(net.getFlow());
                        }
                        break;
                    case 'd':
                        if (initialized(net)) {
                            System.out.println(net);
                        }
                        break;
                    case 'c':
                        if (initialized(net)) {
                            System.out.println(net.getFlow());
                        }
                        break;
                    case 'r':
                        if (initialized(net)) {
                            resNet = net.createResidualNet();
                            System.out.println("Residual net is:");
                            System.out.println(resNet);
                        }
                        break;
                    case 's':
                        if (initialized(resNet)) {
                            niveauGraph = net.createNiveauGraph(resNet);
                            System.out.println("Niveau graph is:\n"
                                    + niveauGraph);
                        }
                        break;
                    case 'h':
                        System.out.print("Hello and welcome to the Algorithm "
                                + " of Dinic \n"
                                + "<file> is the path to a file which you  "
                                + "can use to create a net or a flow \n"
                                + "The file has to follow a certain structure."
                                + "\n The first line declares the number of "
                                + "nodes the net is built upon.\n"
                                + "This can be any number > 1"
                                + "All the following lines are the edges in the"
                                + "net.\n"
                                + "They consist of three numbers. \n"
                                + "The first is the source of the edge.\n"
                                + "The second is the target of the edge.\n"
                                + "The third is the capacity or flow which "
                                + "exists between the source and target node\n"
                                + "The source and target can't be outside the "
                                + "in line 1 given number of nodes. \n"
                                + "The flow and capacity can't be negative "
                                + "numbers.\n"
                                + "This is the List of all commands and how"
                                + " to use them.\n"
                                + "NEW <file>\n"
                                + "Creates a new net.\n"
                                + "FLOW <file>\n"
                                + "Creates a flow in the net.\n"
                                + "MAXFLOW\n"
                                + "Calculates the maximum flow of the net\n"
                                + "PRINTFLOW\n"
                                + "Calculates the maximum flow of the net "
                                + "and prints the Flow aswell."
                                + "DEBUG\n"
                                + "Prints the net\n"
                                + "CURRENTFLOW\n"
                                + "Prints the Flow of the net"
                                + "RESIDUAL\n"
                                + "Prints the ResidualNet of the current net.\n"
                                + "STRICT\n"
                                + "Prints the NiveauGraph of the current net.\n"
                                + "QUIT\n"
                                + "Terminates the program \n");
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

    /**
     * Helper method to create a List of every line in the file provided.
     *
     * @param filename The path of the chosen file.
     * @return The content of the file split by every line.
     * @throws IOException If used in an unintended way.
     */
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

    /**
     * Helper method to check if the file's content can be processed.
     *
     * @param lines The content of a file.
     * @return The converted content which can be processed by the program.
     */
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
                    && isANumber(tokens[0] + tokens[1] + tokens[2])) {
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

    /**
     * Helper method to check the file's content.
     *
     * @param input The content of a file.
     * @return Weather the files arguments can be processed.
     */
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
            // noinspection ResultOfMethodCallIgnored
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Helper method to check if the input can be computed by the program.
     *
     * @param token The user input.
     * @return Weather the command can be processed.
     */
    private static boolean checkInput(String[] token) {
        if (token[0].hashCode() != 0) {
            if (token[0].substring(0, 1).matches("[mpdcrshq]")
                    && token.length == ONE_WORD_COMMAND) {
                return true;
            } else if (token[0].substring(0, 1).matches("[nf]")
                    && token.length == TWO_WORD_COMMAND) {
                return true;
            }
        }
        error("Input is not correct. Try 'Help'"
                + " for a list of viable commands");
        return false;
    }

    /**
     * Helper method to split the input at the first whitespace and reconstruct
     * the second parameter.
     *
     * @param input The command given by the user.
     * @return The command split at the first whitespace.
     */
    private static String[] splitCommand(String input) {
        String[] tokens = input.trim().split("\\s+");
        if (tokens.length > 1) {

            StringBuilder secondParameter = new StringBuilder();
            for (int i = 1; i < tokens.length; i++) {
                secondParameter.append(tokens[i]);
                if (i != tokens.length - 1) {
                    secondParameter.append(" ");
                }
            }
            tokens[1] = secondParameter.toString();
            tokens = new String[]{tokens[0], tokens[1]};
        }
        tokens[0] = tokens[0].toLowerCase();
        return tokens;
    }

    /**
     * Helper method to check if the net has been initialized before further
     * working with it.
     *
     * @param net The for the action required net.
     * @return Weather the net exists.
     */
    private static boolean initialized(ResidualNet net) {
        if (net != null) {
            return true;
        } else {
            error("Initialize a net first!");
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