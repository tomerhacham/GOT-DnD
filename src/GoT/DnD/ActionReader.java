package GoT.DnD;

import GoT.DnD.Persistent_Layer.ReadText;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.EOFException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class ActionReader {
    //Fields
     private boolean deterministicMode=false;
     private List<String> determindAction=null;
     private Iterator<String> iter=null;

    public ActionReader(boolean deterministicMode){
        this.deterministicMode=deterministicMode;
        if(deterministicMode){
            determindAction= ReadText.readAllLines(Paths.get("user_actions.txt"));
            iter=determindAction.iterator();
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
