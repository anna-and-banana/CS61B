package gitlet;

// TODO: any imports you need here
import java.io.File;
import java.nio.file.Path;

import static gitlet.Utils.*;

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class does at a high level.
 *
 *  @author Anna
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    /* The .gitlet path */
    public static final Path GITLET_PATH = GITLET_DIR.toPath();

    /* TODO: fill in the rest of this class. */

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
     *             a commit that contains no files and has the commit message 'initial commit'.
     *             It will have a single branch: 'master',
     *             which initially points to this initial commit, and master will be the current branch.
     *             The timestamp for this initial commit will be 00:00:00 UTC, Thursday, 1 January 1970
     *             in whatever format you choose for dates
     *             (this is called “The (Unix) Epoch”, represented internally by the time 0.)
     */
    public static void init() {

    }
}
