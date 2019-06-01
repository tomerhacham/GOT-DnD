package GoT.DnD.Persistent_Layer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ReadText {

    public static List<String> readAllLines(String level) {
        Path path = FileManager.LocatePath();
        Path s=Paths.get(path.toAbsolutePath().toString(),"\\src\\GoT\\DnD\\Persistent_Layer\\Levels\\");
        Path p = Paths.get(s.toAbsolutePath().toString(),level);
        Path newp = Paths.get(System.getProperty("user.dir")+"\\src\\GoT\\DnD\\Persistent_Layer\\Levels\\"+level);
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(newp);
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + newp);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
        return lines;
    }
}
