import GoT.DnD.Persistent_Layer.ReadText;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class test {
    public static void main(String[] args) {
        File root = new File("C:\\University\\Semester B\\Object Oriented\\Object Oriented Assignment 3\\GOT-DnD\\src\\GoT\\DnD\\Persistent_Layer\\Levels");
        root.listFiles();
        for(File f:        root.listFiles()){
            System.out.println(f.getAbsolutePath());
            System.out.println(f.getAbsolutePath().charAt(f.getAbsolutePath().length()-5));
        }
        List<String> rd = ReadText.readAllLines(Paths.get("user_actions.txt"));
        for(String s:rd){
            System.out.println(s);
        }

        List<String> gd = ReadText.readAllLines(Paths.get("random_numbers.txt"));
        for(String s:gd){
            System.out.println(s);
        }

    }
}
