package gitlet;


import java.io.File;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static gitlet.Utils.*;


public class WorkingArea {

    /**
     * Get working files for Gitlet
     * @return a TreeSet of File
     */
    public static TreeSet<File> getWorkingFiles() {
        return getFilesIn(Repository.CWD).stream()
                                         .filter(f -> !isGitletFile(f))
                                         .collect(Collectors.toCollection(TreeSet::new));
    }
}
