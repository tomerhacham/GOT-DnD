package GoT_DnD;

import GoT_DnD.Business_Layer.GameSystem;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> levels = new LinkedList<>();
        boolean deterministicFlag = false;

        if (args.length != 0) {//some args has been pass

            File root = new File(args[0]); //path for level files
            for (File f : root.listFiles()) {
                levels.add(f.getAbsolutePath());
            }

            if (args.length > 1 && args[1].equals("-D")) {//deterministic flag was raised
                deterministicFlag = true;
            }
        } else {
            File root = new File(System.getProperty("user.dir") + "\\src\\GoT_DnD\\Persistent_Layer\\Levels");
            for (File f : root.listFiles()) {
                levels.add(f.getAbsolutePath());
            }
        }

        GameSystem g = new GameSystem(deterministicFlag, levels);
        g.StartGameFlow();
    }
}
