package GoT.DnD;

import GoT.DnD.Persistent_Layer.ReadText;

import java.nio.file.FileSystems;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List<String> s = ReadText.readAllLines("level 2.txt");
        for (String line:s) {
            System.out.println(line.toString());
        }
    }
}
