package gitlet;

import java.nio.file.Files;


/**
 * Overview: Driver class for Gitlet, a subset of the Git version-control system.
 * Description:
 * @author Anna
 */
public class Main {

    /**
     *  Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            Utils.exitWithError("Please enter a command.");
        }

        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                // Checks the number of arguments versus the expected number/
                validateNumArgs(args, 1);

                // 'init' command should only work while '.gitlet' is not exist.
                if (Files.exists(Repository.GITLET_DIR.toPath())) {
                    Utils.exitWithError("Not in an initialized Gitlet directory.");
                }

                // Initialize Gitlet repository in CWD.
                Repository.init();
                break;

            case "add":
                // TODO: handle the `add [filename]` command

                break;

            // TODO: FILL THE REST IN

            case "commit":

                break;

            case "rm":

                break;

            case "log":

                break;

            case "global-log":

                break;

            case "find":

                break;

            case "status":

                break;

            case "checkout":

                break;

            case "branch":

                break;

            case "rm-branch":

                break;

            case "reset":

                break;

            case "merge":

                break;

            // TODO: Going Remote

            case "add-remote":

                break;

            case "rm-remote":

                break;

            case "push":

                break;

            case "fetch":

                break;

            case "pull":

                break;

            default:
                Utils.exitWithError("No command with that name exists.");
        }
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
            Utils.exitWithError("Incorrect operands.");
        }
    }
}
