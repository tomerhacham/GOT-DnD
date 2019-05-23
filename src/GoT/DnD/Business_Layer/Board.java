package GoT.DnD.Business_Layer;

import GoT.DnD.Persistent_Layer.ReadText;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class Board {

    //Fields
    Character[][] Board;
    LinkedList<GameUnit> GameUnits;
    GameUnit Hero;

    //Constractor
    public Board(String level, GameUnit Hero){
        int x=0;
        int y=0;
        List<String> boardScheme = ReadText.readAllLines(level);
        Board = new Character[boardScheme.size()][boardScheme.get(1).length()]; //Initialize board in the required dimension
        this.Hero=Hero;
        GameUnits = BoardSchemeParser.ParseScheme(boardScheme,Hero);
        for (String line:boardScheme) {
            for (char tile:line.toCharArray()) {
                Board[x][y]=tile;
                x++;
            }
            y++;
        }
    }

    //Methods
    public LinkedList<GameUnit> getGameUnits() {
        return GameUnits;
    }
    public LinkedList<GameUnit> NearbyGameUnits(GameUnit gameunit, Double radius) {
        LinkedList<GameUnit> allCreatures = this.getGameUnits();
        LinkedList<GameUnit> nearbyCreatures = new LinkedList<GameUnit>();
        for (GameUnit creature : allCreatures) {
            if (Range(gameunit, creature) <= radius) {
                nearbyCreatures.addLast(creature);
            }

        }
        return nearbyCreatures;
    }
    public static Double Range(GameUnit gameunit1, GameUnit gameunit2){
        return gameunit1.getPosition().distance(gameunit2.getPosition());
    }



}


