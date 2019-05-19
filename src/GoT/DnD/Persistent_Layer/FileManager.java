package GoT.DnD.Persistent_Layer;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileManager {
    //Fields
    public static Path LocatePath(){
        return FileSystems.getDefault().getPath("").toAbsolutePath();

    }
}
