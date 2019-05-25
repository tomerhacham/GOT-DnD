package GoT.DnD.Business_Layer;

import java.util.LinkedList;
import java.util.List;

public class GameSystem {
    //Fields
    Board board;
    Player Hero;

    //Constructors
    public GameSystem(Player Hero){
        board = new Board("level 1",Hero);
        this.Hero=Hero;
    }
    public void gameTick(){
        //TODO: after action of the Hero from the input
        board.gameTick();
        }
    public void castSpecialAbility(){
        board.castSpecialAbility();

    }
}
