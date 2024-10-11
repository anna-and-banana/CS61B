package gitlet;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;

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

    // The message of this Commit.
    private String msg;
    // The created time of this Commit.
    private Date time;
    // FIXME: only one parent for now.
    // The parent of this Commit.
    private String parent;
    // The Blobs reference
    private TreeMap<String, String> blobs;


    // For debugging
    public static void main(String[] args) {
        System.out.println(sha1(serialize(new Commit())));
    }

    // Initial Commit.
    public Commit() {
        this.msg = "initial commit";
        this.time = new Date(0);
        this.save();
    }


    // Helper method

    /**
     * Save a commit object to file-system at 'saveLocation' with filename 'filename'.
     * @param saveLocation where to save commit object
     * @param filename the name for saving file
     */
    public void save(File saveLocation, String filename) {
        File saveFile = join(saveLocation, filename);
        // Create the file, and write the commit to it.
        try {
            saveFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writeObject(saveFile, serialize(this));
    }

    /**
     * Save a commit object to file-system,
     * at default location 'OBJ_DIR' with default filename (its sha-1 value).
     */
    public void save() {
        save(Repository.OBJ_DIR, sha1(serialize(this)));
    }

    /**
     * Save a commit object to an existing file.
     * @param targetFile the file to store
     */
    public void save(File targetFile) {
        writeObject(targetFile, serialize(this));
    }
}
