package GoT_DnD;

import GoT_DnD.Persistent_Layer.ReadText;

import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class ActionReader {
    //Fields
    private boolean deterministicMode;
    private Iterator<String> iter=null;

    public ActionReader(boolean deterministicMode){
        this.deterministicMode=deterministicMode;
        if(deterministicMode){
            List<String> determindAction = ReadText.readAllLines(Paths.get("user_actions.txt"));
            iter= determindAction.iterator();
        }

    }
    public String nextAction(){
        String toReturn=null;
        if(deterministicMode){
            if(iter.hasNext()){
                toReturn =iter.next();
            }
            else{
                System.out.println("no more action in user_action.txt file");
                System.exit(0);
                }
        }
        else{
            toReturn= Controller.getInput();
            }
        return toReturn;
    }
}
