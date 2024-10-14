package gitlet;


import java.nio.file.Files;

import static gitlet.Utils.*;


public class Head {

    private static final String HEAD_DETACHED_AT = "Head detached at ";

    // The head pointer
    private static Commit reference;
    // The branch that repository currently in
    private static String currentBranch;


    /**
     * Init 'head' while initial repository
     * 1. make 'head' refers to initial commit
     * 2. save 'head' to file 'HEAD'
     */
    public static void init() {
        reference = new Commit();
        currentBranch = "master";
    }


    /* GETTER METHODS */

    public static Commit getReference() {
        return reference;
    }


    /* SETTER METHODS */

    /**
     * Update the Head while a new commit is made
     */
    public static void update(Commit newCommit) {
        reference = newCommit;
        if (isDetached()) {
            currentBranch = HEAD_DETACHED_AT + reference.uid().substring(0, 7);
        }
    }


    /**
     * Checks if 'head' is detached
     */
    private static boolean isDetached() {
        if (currentBranch.contains(HEAD_DETACHED_AT)) {
            return Branch.contains(reference.uid());
        }
        else {
            String commitUID = reference.uid();
            return Files.exists(join(Repository.COMMIT_DIR, commitUID).toPath()) &&
                    Branch.contains(commitUID);
        }
    }


    /* PERSISTENCE */

    /**
     * Save the 'head' to file 'HEAD'
     * save commit UID and current branch name
     */
    public static void saveData() {
        String commitUID = reference.uid();
        saveToFile(Repository.HEAD_FILE, commitUID + currentBranch);
    }

    /**
     * Load file 'HEAD' to the 'head'
     * 1. load the contents of 'HEAD' file, which contains some text.
     * 2. the contents composed with commit id and branch name:
     *      (1) commit UID
     *      (2) current branch name
     */
    public static void loadData() {
        String contents = loadFromFile(Repository.HEAD_FILE, String.class);
        loadReference(contents);
        loadCurrentBranch(contents);
    }

    private static void loadReference(String contents) {
        String commitUID = contents.substring(0, UID_LENGTH);
        reference = Commit.load(commitUID);
    }

    private static void loadCurrentBranch(String contents) {
        currentBranch = contents.substring(UID_LENGTH);
    }
}
