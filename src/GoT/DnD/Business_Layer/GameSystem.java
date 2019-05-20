package GoT.DnD.Business_Layer;

import java.util.LinkedList;

public class GameSystem {
    //Fields
    Board board;
    GameSystem Hero;

    //Constructors
    public GameSystem(){
        //TODO:initialize board by level and maintain it
        //TODO: handle hero
    }
    public Double Range(GameUnit gameunit1, GameUnit gameunit2){
        Double Range=0.0;
        Range = Math.pow(gameunit1.getPosition()[0] - gameunit2.getPosition()[0],2.0)+Math.pow(gameunit1.getPosition()[1] - gameunit2.getPosition()[1],2.0);
        Range = Math.sqrt(Range);
        return Range;
    }
    public LinkedList<GameUnit> NearbyGameUnits(GameUnit gameunit, Double radius){
        LinkedList<GameUnit> allCreatures = board.getGameUnits();
        LinkedList<GameUnit> nearbyCreatures = new LinkedList<GameUnit>();
        for (GameUnit creature :allCreatures) {
            if(Range(gameunit,creature)<=radius){
                nearbyCreatures.addLast(creature);
            }

        }
        return nearbyCreatures;
    }
}
