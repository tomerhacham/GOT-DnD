package GoT.DnD.Business_Layer;

import java.util.LinkedList;

public class GameSystem {
    //Fields
    Board board;
    GameUnit Hero;

    //Constructors
    public GameSystem(GameUnit Hero){
        //TODO:initialize board by level and maintain it
        this.Hero=Hero;
    }
    public void castSpecialAbility() {

        LinkedList<GameUnit> nearbyCreatures = NearbyGameUnits(Hero,Hero.getRange())

    }



    }
}
