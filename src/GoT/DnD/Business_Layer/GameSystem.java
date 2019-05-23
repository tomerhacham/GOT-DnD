package GoT.DnD.Business_Layer;

import java.util.LinkedList;
import java.util.List;

public class GameSystem {
    //Fields
    Board board;
    GameUnit Hero;

    //Constructors
    public GameSystem(GameUnit Hero){
        //TODO:initialize board by level and maintain it
        this.Hero=Hero;
    }
    public void Round(){
        //TODO: after action of the Hero from the input
        List<GameUnit> gameUnits = board.getGameUnits();
        for(GameUnit gu:gameUnits){
        }

    }
}
