package GoT.DnD.Business_Layer;

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
            //TODO: call Trap builder and pass the tile
        }
        else{
            //TODO: call Monster builder and pass the tile
        }
        return unit;
    }
}
