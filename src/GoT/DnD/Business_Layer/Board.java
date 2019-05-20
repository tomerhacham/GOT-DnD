package GoT.DnD.Business_Layer;

import GoT.DnD.Persistent_Layer.ReadText;

import java.util.LinkedList;
import java.util.List;

public class Board {

    //Fields
    Character[][] Board;
    LinkedList<GameUnit> GameUnits;

    //Constractor
    public Board(String level){
        int x=0;
        int y=0;
        List<String> boardScheme = ReadText.readAllLines(level);
        Board = new Character[boardScheme.size()][boardScheme.get(1).length()]; //Initialize board in the required dimension
        GameUnits = BoardSchemeParser.ParseScheme(boardScheme);
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




}


