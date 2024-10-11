package gitlet;


import java.io.File;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static gitlet.Utils.*;


/**
 *  TODO: It's a good idea to give a description here of what else this Class does at a high level.
 *  Overview: Represents a gitlet repository.
 *  Description:
 *  @author Anna
 */
public class Repository {

    // The current working directory.
    public static final File CWD = new File(System.getProperty("user.dir"));
    // The '.gitlet' directory, like the '.git' directory.
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    // The 'HEAD' file, which stores HEAD pointer's data.
    public static final File HEAD_FILE = join(GITLET_DIR, "HEAD");
    // The 'objects' directory, which stores all objects including commits, blobs.
    public static final File OBJ_DIR = join(GITLET_DIR, "objects");
    // The 'staging_area' file.
    public static final File STAGING_AREA_FILE = join(GITLET_DIR, "staging_area");

    // '.gitlet' persistence structure
    private static final Map<File, String> GITLET_DIR_STRUCTURE = Map.of(
            GITLET_DIR, "dir",
            HEAD_FILE, "file",
            OBJ_DIR, "dir",
            STAGING_AREA_FILE, "file"
    );

    // The 'head' pointer refers the 'commit' that the repository is currently in.
    private static Commit head;
    // The working files in Gitlet repository
    private static TreeSet<File> workingFiles;


    public static void main(String[] args) {
        setupPersistence();
        workingFiles.forEach(System.out::println);
    }

    /**
     * Usage: 'java gitlet.Main init'
     * <p>
     * Description: creates a new Gitlet version-control system in the current directory.
     * <p>
     * Details: 1. make persistence:
     *             .gitlet
     *             ├── HEAD
     *             ├── objects
     *             │   └── ... (commits, blobs)
     *             └── staging_area
     *          2. This system will automatically start with one commit:
     *             a commit that contains no files
     *             and has the commit message "initial commit".
     *             The timestamp for this initial commit will be
     *             00:00:00 UTC, Thursday, 1 January 1970
     *             in whatever format you choose for dates
     *             (this is called “The (Unix) Epoch”,
     *             represented internally by the time 0.)
     *           3. It will have a single branch: 'master',
     *              which initially points to this initial commit,
     *              and master will be the current branch.
     */
    public static void init() {
        // FIXME: version1, without branching

        // Make persistence.
        setupPersistence();
        // Create initial commit, and make the 'head' pointer refers to it,
        head = new Commit();
        // Save 'head' to HEAD file.
        head.save(HEAD_FILE);
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


    // Getter method

    /**
     *  Get repository's head pointer.
     *  @return the 'Commit' object of head pointer refers to
     */
    public static Commit getHeadPointer() {
        return head;
    }


    // Helper method

    /**
     *  Setup Gitlet repository persistence
     *  structure: .gitlet
     *             ├── HEAD
     *             ├── objects
     *             │   └── ...
     *             └── staging_area
     */
    private static void setupPersistence() {
        GITLET_DIR_STRUCTURE.forEach(Utils::createFileOrDirectory);
    }

}
