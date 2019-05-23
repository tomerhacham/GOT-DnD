package GoT.DnD.Business_Layer;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class BoardSchemeParser {
    static final char Empty = '.';
    static final char Wall = '#';

    public static LinkedList<GameUnit> ParseScheme(List<String> boardScheme){
        LinkedList<GameUnit> GameUnits = new LinkedList<GameUnit>();
        for (String line:boardScheme) {
            for (char tile:line.toCharArray()) {
                if(tile=='@'){
                    //TODO: take the Hero reference
                    //TODO: add hero as the first element of the list
                }
                else if(tile!=Empty && tile!=Wall){
                    GameUnit gameUnit=GameUnitBuilder(tile);
                    GameUnits.addLast(gameUnit);
                }

            }
        }
        return GameUnits;

    }

    public static GameUnit GameUnitBuilder(char tile){
        GameUnit unit;
        if(tile!='B' && tile!='Q' && tile!='D'){
            switch (tile){
                case 'B': unit = new Trap("Bonus Trap",1,1,1,new Point(0,0),250,tile,5,6,2);
                        break;
                case 'Q':unit = new Trap("Queen's Trap",250,50,10,new Point(0,0),100,tile,4,10,4);
                        break;
                case 'D':
            }
        }
        else{
            //TODO: call Monster builder and pass the tile
        }
        return unit;
    }
}
