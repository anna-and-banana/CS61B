import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Test {

    static File cwd = new File(System.getProperty("user.dir"));

    public static void main(String[] args) {
        printAllFilesIn(cwd);
    }

    public static void printAllFilesIn(File dir) {
        try {
            Files.walk(dir.toPath())
                    .map(Path::toFile)
                    .map(x -> getRelativePath(x, cwd))
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get relative path of a 'file' from 'reference'
     * @param file the file to get relative path
     * @param reference the reference to determines relative path
     * @return a string of relative path
     */
    public static String getRelativePath(File file, File reference) {
        return reference.toPath().relativize(file.toPath()).toString();
    }
}
