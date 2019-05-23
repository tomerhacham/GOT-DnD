package GoT.DnD.Business_Layer;

import GoT.DnD.Persistent_Layer.ReadText;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class Board {

    //Fields
    private static Character[][] board;
    private LinkedList<GameUnit> GameUnits;
    LinkedList<GameUnit> GameUnits;
    GameUnit Hero;

    static final char EMPTY = '.';
    static final char WALL = '#';

    //Constructor
    public Board(String level){
        int x=0;
        int y=0;
        List<String> boardScheme = ReadText.readAllLines(level);
        board = new Character[boardScheme.size()][boardScheme.get(1).length()]; //Initialize board in the required dimension
        GameUnits = BoardSchemeParser.ParseScheme(boardScheme);
        this.Hero=Hero;
        GameUnits = BoardSchemeParser.ParseScheme(boardScheme,Hero);
        for (String line:boardScheme) {
            for (char tile:line.toCharArray()) {
                board[x][y]=tile;
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

    public static boolean isLegalMove(Point pos, int move){
        Point p = new Point(pos.x, pos.y);
        if ((move == 1 && board[p.x][p.y+1] == EMPTY) || (move == 2 && board[p.x][p.y-1] == EMPTY) || (move == 3 && board[p.x+1][p.y] == EMPTY) || (move == 4 && board[p.x-1][p.y] == EMPTY)){
            return true;
        } else {

        }

    }





}


