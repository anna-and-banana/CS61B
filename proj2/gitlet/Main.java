package gitlet;

import java.nio.file.Files;
import java.nio.file.Path;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author Anna
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        if (args.length == 0) {
            exitWithError("Please enter a command.");
        }

        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                // TODO: handle the `init` command
                /** Checks the number of arguments versus the expected number,
                 *  exits if not expected */
                validateNumArgs(args, 1);

                /** Checks if the directory '.gitlet' exists,
                 *  if it exists, then print out "Not in an initialized Gitlet directory.",
                 *  and exits with error code 0. */
                if (Files.exists(Repository.GITLET_PATH)) {
                    exitWithError("Not in an initialized Gitlet directory.");
                }

                /* Initialise Gitlet repository in CWD */
                Repository.init();
                break;
            case "add":
                // TODO: handle the `add [filename]` command

                break;
            // TODO: FILL THE REST IN

            default:
                exitWithError("No command with that name exists.");
        }
    }

    /**
     * Prints out MESSAGE and exits with error code 0.
     * @param message message to print
     */
    private static void exitWithError(String message) {
        Utils.message(message);
        System.exit(0);
    }

    /**
     * Checks the number of arguments versus the expected number,
     * prints out 'Incorrect operands.' and exits with error code 0 if they do not match.
     *
     * @param args Argument array from command line
     * @param n Number of expected arguments
     */
    private static void validateNumArgs(String[] args, int n) {
        if (args.length != n) {
            exitWithError("Incorrect operands.");
        }
    }
}
