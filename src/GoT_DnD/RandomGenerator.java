package GoT_DnD;

import GoT_DnD.Persistent_Layer.ReadText;

import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {

    private boolean deterministicMode;
    private Iterator<String> iter=null;

    public RandomGenerator(boolean deterministicMode) {
        this.deterministicMode=deterministicMode;
        if(deterministicMode){
            List<String> random_number_list = ReadText.readAllLines(Paths.get("random_numbers.txt"));
            iter= random_number_list.iterator();
        }

    }

    public int nextInt(int n){
        int toReturn=0;
        if(deterministicMode){
            if(iter.hasNext()){
                toReturn = Integer.parseInt(iter.next());
            }
            else{
                System.out.println("no more action in random_numbers file");
                System.exit(0);
            }
        }
        else{
            toReturn= ThreadLocalRandom.current().nextInt(0, n+1);
            }
        return toReturn;
    }
}
