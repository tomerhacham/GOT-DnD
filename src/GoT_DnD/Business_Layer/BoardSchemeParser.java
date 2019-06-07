package GoT_DnD.Business_Layer;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class BoardSchemeParser {
    static final char Empty = '.';
    static final char Wall = '#';

    /**
     * Parsing the board and buildng all the monster' traps and the hero in the right location
     * @param boardScheme list of strings that represent all the chars of the board
     * @param Hero the Hero object to relocate on the board by the spawn point (@ char location)
     * @return list of all the Gameunits of the board where the Hero object is the First element of the list
     */
    public static LinkedList<GameUnit> ParseScheme(List<String> boardScheme,GameUnit Hero){
        LinkedList<GameUnit> GameUnits = new LinkedList<GameUnit>();
        int x=0,y=0;
        for (String line:boardScheme) {
            for (char tile:line.toCharArray()) {
                Point position = new Point(x,y);
                if(tile=='@'){
                    Hero.setPosition(position);
                    GameUnits.addFirst(Hero);
                }
                else if(tile!=Empty && tile!=Wall){
                    GameUnit gameUnit=GameUnitBuilder(tile,position,Hero);
                    GameUnits.addLast(gameUnit);
                }
                y++;
            }
            y=0;
            x++;
        }
        return GameUnits;

    }

    /**
     * Build the gameunit by the char that represent it on the board
     * @param tile the char which represent the Enemy
     * @param position the position to relocate the Enemy
     * @return constructed Enemy already positioned on the board
     */
    public static GameUnit GameUnitBuilder(char tile,Point position,GameUnit Hero){
        GameUnit unit=null;
        if(tile=='B' || tile=='Q' || tile=='D'){
            switch (tile){
                case 'B': unit = new Trap("Bonus Trap",1,1,1,position,250,tile,5,6,2 ,Hero);
                        break;
                case 'Q':unit = new Trap("Queen's Trap",250,50,10,position,100,tile,4,10,4,Hero);
                        break;
                case 'D':unit = new Trap("Death Trap",500,100,20,position,250,tile,6,10,3,Hero);
                        break;
            }
        }
        else{
            switch (tile){
                case 's': unit = new Monster("Lannister Solider",80,8,3,position,25,tile,3,Hero);
                          break;
                case 'k': unit = new Monster("Lannister Knight",200,14,8,position,50,tile,4,Hero);
                          break;
                case 'q': unit = new Monster("Queen's Guard",400,20,15,position,100,tile,5,Hero);
                          break;
                case 'z': unit = new Monster("Wright",600,30,15,position,100,tile,3,Hero);
                          break;
                case 'b': unit = new Monster("Bear-Wright",1000,75,30,position,250,tile,4,Hero);
                    break;
                case 'g': unit = new Monster("Giant-Wright",1500,100,40,position,500,tile,5,Hero);
                    break;
                case 'w': unit = new Monster("White Walker",2000,150,50,position,1000,tile,6,Hero);
                    break;
                case 'M': unit = new Monster("The Mountain",1000,60,25,position,500,tile,6,Hero);
                    break;
                case 'C': unit = new Monster("Queen Cersei",100,10,10,position,1000,tile,1,Hero);
                    break;
                case 'K': unit = new Monster("Night's King",5000,300,150,position,5000,tile,8,Hero);
                    break;
            }
        }
        return unit;
    }
}
