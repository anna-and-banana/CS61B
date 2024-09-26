package gitlet;

// TODO: any imports you need here

import java.util.Date;

/**
 *  TODO: It's a good idea to give a description here of what else this Class does at a high level.
 *  Overview: Represents a gitlet commit object.
 *  Description:
 *  @author Anna
 */
public class Commit {
    /**
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    // The message of this Commit.
    private String msg;
    // The timestamp of this Commit.
    private Date timestamp;
    // The parent of this Commit.
    private Commit parent;
    // Some other stuff: The Blobs reference...


    // Initial Commit.
    public Commit() {
        this.msg = "initial commit";
        this.timestamp = new Date(0);
        this.parent = null;
    }
}
