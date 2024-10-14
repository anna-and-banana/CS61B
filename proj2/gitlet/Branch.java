package gitlet;


import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static gitlet.Utils.*;


public class Branch {

    // All branch references in repository.
    private static final Map<String, String> references = new TreeMap<>();


    public static void init() {
        create("master");
    }

    public static boolean contains(String commitUID) {
        return !references.containsValue(commitUID);
    }


    /* PERSISTENCE */

    /**
     * Make a new branch of name 'branchName'
     * 1. add branch reference in 'branchRefs'
     * 2. create branch file and save it
     */
    public static void create(String branchName) {
        String commitID = Head.getReference().uid();
        references.put(branchName, commitID);
    }

    public static void loadData() {
        List<File> branchFiles = getFilesIn(Repository.LOCAL_BRANCH_DIR);
        for (File branchFile : branchFiles) {
            String branchName = branchFile.getName();
            String commitID = loadFromFile(branchFile, String.class);
            references.put(branchName, commitID);
        }
    }

    public static void saveData() {
        references.forEach((branchName, commitID) ->
                saveToFile(join(Repository.LOCAL_BRANCH_DIR, branchName), commitID));
    }
}
