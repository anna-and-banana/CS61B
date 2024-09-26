package gitlet;


import java.io.File;

import static gitlet.Utils.*;


/**
 *  TODO: It's a good idea to give a description here of what else this Class does at a high level.
 *  Overview: Represents a gitlet repository.
 *  Description:
 *  @author Anna
 */
public class Repository {
    /**
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    // The current working directory.
    public static final File CWD = new File(System.getProperty("user.dir"));
    // The '.gitlet' directory.
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    // The 'staging_area' directory.
    public static final File STAGING_AREA_DIR = join(GITLET_DIR, "staging_area");
    // The 'commits' directory.
    public static final File COMMIT_DIR = join(GITLET_DIR, "commits");
    // The 'blobs' directory.
    public static final File BLOBS_DIR = join(GITLET_DIR, "blobs");


    // TODO: init()
    /**
     * Usage: 'java gitlet.Main init'
     * Description: creates a new Gitlet version-control system in the current directory.
     * Details: 1. make persistence
     *             .gitlet
     *              ├── staging_area
     *              │   ├── staged_for_addition
     *              │   └── staged_for_removal
     *              ├── commits
     *              └── blobs
     *          2. This system will automatically start with one commit:
     *             a commit that contains no files and has the commit message "initial commit".
     *             - It will have a single branch: 'master',
     *               which initially points to this initial commit, and master will be the current branch.
     *             - The timestamp for this initial commit will be 00:00:00 UTC, Thursday, 1 January 1970
     *               in whatever format you choose for dates
     *               (this is called “The (Unix) Epoch”, represented internally by the time 0.)
     */
    public static void init() {
        // Make persistence.
        setupPersistence();

        // Create initial commit.
        Commit initialCommit = new Commit();

        // TODO: Other things to do
    }

    // TODO: add()
    /**
     * Usage: java gitlet.Main add [file name]
     * Description:
     * Details:
     */


    // TODO: commit()
    /**
     * Usage: java gitlet.Main commit [message]
     * Description:
     * Details:
     */


    // Helper method

    /**
     *  Setup Gitlet repository persistence
     *  structure: .gitlet
     *              ├── staging_area
     *              │   ├── staged_for_addition     <== file or directory?
     *              │   └── staged_for_removal
     *              ├── commits
     *              └── blobs
     */
    private static void setupPersistence() {
        // Create structure.
        GITLET_DIR.mkdir();
        STAGING_AREA_DIR.mkdir();
        COMMIT_DIR.mkdir();
        BLOBS_DIR.mkdir();
    }
}
