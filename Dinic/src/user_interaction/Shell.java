package user_interaction;

import java.io.*;

public final class Shell {
    public Shell() {
    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));



        File file = new File("input.txt");
        if (file.exists() && file.isFile()) {
            BufferedReader in = new BufferedReader(new FileReader(file));
            boolean eof = false;
            while (!eof) {
                String input = in.readLine();
                if (input == null) {
                    eof = true;
                } else {
                    System.out.println(input);
                }
            }
            in.close();
        }
    }

    private static void execute(BufferedReader stdin) throws IOException {
        boolean quit = false;
        while (!quit) {
            System.out.println("maxflow> ");
            String input = stdin.readLine();
            String[] tokens = input.trim().split("\\s+");


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


    private static boolean checkInput(String string) {
        return string.hashCode() != 0
                && string.substring(0, 1).matches("[nfmpdcrshq]]");
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
