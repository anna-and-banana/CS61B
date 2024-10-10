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

            /* Usage: java gitlet.Main init
             * Creates a new Gitlet version-control system in the current directory. */
            case "init":
                validateNumArgs(args, 1);

                if (isInitialized()) {
                    // 'init' command should only work while '.gitlet' is not exist.
                    Utils.exitWithError("A Gitlet version-control system " +
                            "already exists in the current directory.");

                } else {
                    // Initialize Gitlet repository in CWD.
                    Repository.init();
                }
                break;

            /*  Usage: java gitlet.Main add [file name]
             *
             *  Description: Adds a copy of the file as it currently exists
             *              to the staging area (see the description of the commit command).
             *              For this reason,
             *              adding a file is also called staging the file for addition.
             *              Staging an already-staged file overwrites
             *              the previous entry in the staging area with the new contents.
             *              The staging area should be somewhere in .gitlet.
             *              If the current working version of the file
             *              is identical to the version in the current commit,
             *              do not stage it to be added,
             *              and remove it from the staging area if it is already there
             *              (as can happen when a file is changed, added,
             *              and then changed back to it’s original version).
             *              The file will no longer be staged for removal (see gitlet rm),
             *              if it was at the time of the command.
             *
             *  Runtime: In the worst case, should run in linear time
             *           relative to the size of the file being added and lgN,
             *           for N the number of files in the commit.
             *
             *  Failure cases: If the file does not exist,
             *                 print the error message "File does not exist."
             *                 and exit without changing anything.
             *
             *  Dangerous?: No
             *
             *  Our line count: ~20
             *
             *  Differences from real git: In real git,
             *                             multiple files may be added at once.
             *                             In gitlet, only one file may be added at a time.
             */
            case "add":
                validateNumArgs(args, 2);
                requireInitialized();


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
     *  Checks the number of arguments versus the expected number,
     *  prints out "Incorrect operands." ,
     *  and exits with error code 0 if they do not match.
     *
     *  @param args Argument array from command line
     *  @param n Number of expected arguments
     */
    private static void validateNumArgs(String[] args, int n) {
        if (args.length != n) {
            Utils.exitWithError("Incorrect operands.");
        }
    }

    /**
     *  Checks if the repository is initialized (i.e. is '.gitlet' directory exist).
     *  @return true if initialized, false otherwise
     */
    private static boolean isInitialized() {
        return Files.exists(Repository.GITLET_DIR.toPath());
    }

    /**
     *  If repository is not initialized, it will print error message,
     *  and exits with code 0.
     */
    private static void requireInitialized() {
        if (!isInitialized()) {
            Utils.exitWithError("Not in an initialized Gitlet directory.");
        }
    }
}
