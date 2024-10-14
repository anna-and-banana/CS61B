package gitlet;


import java.io.File;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


import static gitlet.Utils.*;


/**
 * TODO: It's a good idea to give a description here of what else this Class does at a high level.<p>
 * Overview: Represents a gitlet commit object.
 * Description: A 'commit' mainly records 2 things:
 *              1. The status of the repository at a certain timestamp.
 *              2. The metadata while commiting, which are 'commit message' and 'timestamp'.
 * @author Anna
 */
public class Commit implements Serializable {

    // The created timestamp of this Commit.
    private Instant timestamp;
    // The message of this Commit.
    private String msg;
    // The parents of this Commit.
    private String[] parents = new String[2];
    // The Blobs reference
    private TreeMap<String, String> blobReferences;


    /* CONSTRUCTOR */

    // Initial Commit
    public Commit() {
        this.timestamp = Instant.EPOCH;
        this.msg = "initial commit";
        this.save();
    }

    // Regular Commit
    public Commit(String msg) {
        this.timestamp = Instant.now();
        this.msg = msg;
        this.parents[0] = Head.getReference().uid();
        this.blobReferences = Head.getReference().blobReferences;
        Head.update(this);
    }


    /* SAVE AND LOAD */

    /**
     * Save a commit object to file-system,
     * at default location 'OBJ_DIR' with default filename (its sha-1 value).
     */
    public void save() {
        File savePath = join(Repository.COMMIT_DIR, this.uid());
        saveToFile(savePath, this);
    }

    /**
     * Read a commit file to a Commit object
     * @param commitFile a commit file
     * @return a Commit object
     */
    public static Commit load(File commitFile) {
        return loadFromFile(commitFile, Commit.class);
    }

    public static Commit load(String commitID) {
        return load(join(Repository.COMMIT_DIR, commitID));
    }

    public static List<Commit> loadAll() {
        List<File> commitFiles = getFilesIn(Repository.COMMIT_DIR);
        return commitFiles.stream()
                          .map(Commit::load)
                          .collect(Collectors.toList());
    }


    /* FUNCTIONAL METHOD */

    /**
     * Return the commits UID, i.e. its sha-1 value.
     * @return a String of its sha-1 value
     */
    public String uid() {
        return sha1(serialize(this));
    }

    public boolean isMerged() {
        return this.parents[1] != null;
    }

    /**
     * Return commits history start from 'this' commit
     * @return a List of commit
     */
    public List<Commit> history() {
        if (this.parents[0] == null) {
            return List.of(this);
        }
        List<Commit> commits = new ArrayList<>(List.of(this));
        Commit parent = load(join(Repository.COMMIT_DIR, this.parents[0]));
        commits.addAll(parent.history());
        return commits;
    }


    @Override
    public String toString() {

        class Representation {

            static String regular(Commit commit) {
                return title() + commit(commit) + date(commit) + message(commit);
            }

            static String merged(Commit commit) {
                return title() + commit(commit) + merge(commit) + date(commit) + message(commit);
            }

            static String title() {
                return "===" + "\n";
            }

            static String commit(Commit commit) {
                return "commit " + commit.uid() + "\n";
            }

            static String date(Commit commit) {
                return "Date: " + commit.formatedTimestamp() + "\n";
            }

            static String merge(Commit commit) {
                return "Merge: " + commit.parents[0].substring(0, 7) + " " +
                        commit.parents[1].substring(0, 7) + "\n";
            }

            static String message(Commit commit) {
                return commit.msg + "\n";
            }
        }

        if (this.isMerged()) {
            return Representation.merged(this);
        }
        return Representation.regular(this);
    }


    /* HELPER METHOD */

    /**
     * Format timestamp of a commit
     * @return the formatted timestamp
     */
    private String formatedTimestamp() {
        // Setup timezone
        ZonedDateTime zonedDateTime = this.timestamp.atZone(ZoneId.systemDefault());
        // Create time formatter
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy Z");

        return zonedDateTime.format(timeFormatter);
    }
}
