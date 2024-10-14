package gitlet;


import java.io.File;
import java.io.Serializable;
import java.util.*;


import static gitlet.Utils.*;


/**
 *  TODO: It's a good idea to give a description here of what else this Class does at a high level.
 *  Overview: Represents a gitlet repository.
 *  Description:
 *  @author Anna
 */
public class Repository {

    /* DEBUGGING */
    public static void main(String[] args) {
        init();
        new Commit("1").save();
        new Commit("2").save();
        new Commit("3").save();
        log();
        System.out.println("--------------");
        globalLog();
    }


    /*
     * PERSISTENCE
     */

    /* GITLET DIRECTORY STRUCTURE */

    // The current working directory.
    public static final File CWD = new File(System.getProperty("user.dir"));
    // The '.gitlet' directory, like the '.git' directory.
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    // The 'blobs' directory that stores all blobs in it.
    public static final File BLOB_DIR = join(GITLET_DIR, "blobs");
    // The 'commits' directory that stores all commits in it.
    public static final File COMMIT_DIR = join(GITLET_DIR, "commits");
    // The 'HEAD' file that stores HEAD pointer's data.
    public static final File HEAD_FILE = join(GITLET_DIR, "HEAD");
    // The 'refs' directory that stores head pointers of branch including local and remote.
    public static final File REF_DIR = join(GITLET_DIR, "refs");
    // The 'heads' directory that stores head pointers of local branchRefs.
    public static final File LOCAL_BRANCH_DIR = join(REF_DIR, "heads");
    // The 'staging_area' file that stores staging files to be addition or removal.
    public static final File STAGING_FILE = join(GITLET_DIR, "staging_area");

    // '.gitlet' persistence structure
    private static final Map<File, String> GITLET_DIR_STRUCTURE = Map.of(
            GITLET_DIR, "dir",
            BLOB_DIR, "dir",
            COMMIT_DIR, "dir",
            HEAD_FILE, "file",
            REF_DIR, "dir",
            LOCAL_BRANCH_DIR, "dir",
            STAGING_FILE, "file"
    );

    /* PERSISTENCE METHODS */

    /**
     *  Setup Gitlet repository persistence
     */
    private static void setupPersistence() {
        GITLET_DIR_STRUCTURE.forEach(Utils::createFileOrDirectory);
    }

    // TODO: add more data
    /* SAVE AND LOAD */
    public static void saveData() {
        Head.saveData();
        Branch.saveData();
    }

    public static void loadData() {
        Head.loadData();
        Branch.loadData();
    }


    /*
     * GITLET REPOSITORY FUNCTIONAL METHODS
     */

    /**
     * Usage: 'java gitlet.Main init'
     * <p>
     * Description: creates a new Gitlet version-control system in the current directory.
     * <p>
     * Details: 1. make persistence:
     *             .gitlet
     *             ├── blobs
     *             ├── commits
     *             ├── HEAD
     *             ├── refs
     *             │   └── heads
     *             │       └── ... (head pointers of branch)
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
        setupPersistence();
        Head.init();
        Branch.init();
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


    /**
     * Usage: java gitlet.Main log
     * Description: prints commits log
     * Details: printing start from head
     */
    public static void log() {
        Head.getReference()
            .history()
            .forEach(System.out::println);
    }

    /**
     * Usage: java gitlet.Main global-log
     * Description: Prints all commits
     * Details: 1. Prepare all Commit objects
     *          2. prints each commit
     */
    public static void globalLog() {
        Commit.loadAll()
              .forEach(System.out::println);
    }


    /*
     * HELPER METHODS
     */

}

