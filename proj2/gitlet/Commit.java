package gitlet;


import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 *  TODO: It's a good idea to give a description here of what else this Class does at a high level.<p>
 *  Overview: Represents a gitlet commit object.
 *  Description: A 'commit' mainly records 2 things:
 *               1. The status of the repository at a certain timestamp.
 *               2. The metadata while commiting, which are 'commit message' and 'timestamp'.
 *  @author Anna
 */
public class Commit implements Serializable {

    // The message of this Commit.
    private String msg;
    // The timestamp of this Commit.
    private Date timestamp;
    // The parents of this Commit.
    private TreeSet<String> parents;
    // The Blobs reference
    private TreeMap<String, String> blobs;


    // For debugging
    public static void main(String[] args) {
        Commit c = new Commit();

        if (c.parents == null) {
            System.out.println("parents is null");
        }
        if (c.blobs == null) {
            System.out.println("blobs are null");
        }
    }

    // Initial Commit.
    public Commit() {
        this.msg = "initial commit";
        this.timestamp = new Date(0);
    }
}
