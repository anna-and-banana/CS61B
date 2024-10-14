package gitlet;


import java.nio.file.Files;

import static gitlet.Utils.*;


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
            exitWithError("Please enter a command.");
        }

        String firstArg = args[0];
        switch(firstArg) {

            /* Usage: java gitlet.Main init
             * Creates a new Gitlet version-control system in the current directory. */
            case "init":
                validateNumArgs(args, 1);

                if (isInitialized()) {
                    // 'init' command should only work while '.gitlet' is not exist.
                    exitWithError("A Gitlet version-control system " +
                            "already exists in the current directory.");

                } else {
                    // Initialize Gitlet repository in CWD.
                    Repository.init();
                    Repository.saveData();
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
                Repository.loadData();
                // TODO:
                Repository.saveData();
                break;

            // TODO: FILL THE REST IN

            case "commit":

                break;

            case "rm":

                break;

            /* Usage: java gitlet.Main log
             *
             * Description: Starting at the current head commit,
             *              display information about each commit backwards
             *              along the commit tree until the initial commit,
             *              following the first parent commit links,
             *              ignoring any second parents found in merge commits.
             *              (In regular Git, this is what you get with 'git log --first-parent').
             *              This set of commit nodes is called the commit’s history.
             *              For every node in this history,
             *              the information it should display is
             *              the commit id,
             *              the time the commit was made,
             *              and the commit message.
             *              Here is an example of the exact format it should follow:
             *
             *              ===
             *              commit a0da1ea5a15ab613bf9961fd86f010cf74c7ee48
             *              Date: Thu Nov 9 20:00:05 2017 -0800
             *              A commit message.
             *
             *              ===
             *              commit 3e8bf1d794ca2e9ef8a4007275acf3751c7170ff
             *              Date: Thu Nov 9 17:01:33 2017 -0800
             *              Another commit message.
             *
             *              ===
             *              commit e881c9575d180a215d1a636545b8fd9abfb1d2bb
             *              Date: Wed Dec 31 16:00:00 1969 -0800
             *              initial commit
             *
             *              There is a === before each commit and an empty line after it.
             *              As in real Git, each entry displays the unique SHA-1 id
             *              of the commit object.
             *              The timestamps displayed in the commits
             *              reflect the current timezone, not UTC;
             *              as a result, the timestamp for the initial commit
             *              does not read Thursday, January 1st, 1970, 00:00:00,
             *              but rather the equivalent Pacific Standard Time.
             *              Your timezone might be different depending on where you live,
             *              and that’s fine.
             *
             *              Display commits with the most recent at the top.
             *              By the way, you’ll find that the Java classes
             *              java.util.Date and java.util.Formatter
             *              are useful for getting and formatting times.
             *              Look into them instead of trying to
             *              construct it manually yourself!
             *
             *              Of course, the SHA1 identifiers are going to be different,
             *              so don’t worry about those.
             *              Our tests will ensure that
             *              you have something that “looks like” a SHA1 identifier
             *              (more on that in the testing section below).
             *
             *              For merge commits (those that have two parent commits),
             *              add a line just below the first, as in
             *
             *              ===
             *              commit 3e8bf1d794ca2e9ef8a4007275acf3751c7170ff
             *              Merge: 4975af1 2c1ead1
             *              Date: Sat Nov 11 12:30:00 2017 -0800
             *              Merged development into master.
             *
             *              where the two hexadecimal numerals following “Merge:”
             *              consist of the first seven digits of
             *              the first and second parents’ commit ids,in that order.
             *              The first parent is the branch you were on
             *              when you did the merge;
             *              the second is that of the merged-in branch.
             *              This is as in regular Git.
             *
             * Runtime: Should be linear
             *          with respect to the number of nodes in head’s history.
             *
             * Failure cases: None
             *
             * Dangerous?: No
             *
             * Our line count: ~20
             *
             * Here’s a picture of the history of a particular commit.
             * If the current branch’s head pointer happened to be pointing to that commit,
             * log would print out information about the circled commits:
             *
             * https://sp21.datastructur.es/materials/proj/proj2/image/history.png
             *
             * The history ignores other branches and the future.
             * Now that we have the concept of history,
             * let’s refine what we said earlier about the commit tree being immutable.
             * It is immutable precisely
             * in the sense that the history of a commit
             * with a particular id may never change, ever.
             * If you think of the commit tree as nothing
             * more than a collection of histories,
             * then what we’re really saying is that each history is immutable.
             */
            case "log":
                validateNumArgs(args, 1);
                requireInitialized();
                Head.loadData();
                Repository.log();
                break;

            /* Usage: java gitlet.Main global-log
             *
             * Description: Like log, except displays information
             *              about all commits ever made.
             *              The order of the commits does not matter.
             *              Hint: there is a useful method in gitlet.Utils
             *              that will help you iterate over files within a directory.
             *
             * Runtime: Linear with respect to the number of commits ever made.
             *
             * Failure cases: None
             *
             * Dangerous?: No
             *
             * Our line count: ~10
             */
            case "global-log":
                validateNumArgs(args, 1);
                requireInitialized();
                Repository.globalLog();
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
                exitWithError("No command with that name exists.");
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
            exitWithError("Incorrect operands.");
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
            exitWithError("Not in an initialized Gitlet directory.");
        }
    }
}
