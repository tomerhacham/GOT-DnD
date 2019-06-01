package GoT.DnD.Business_Layer;

import GoT.DnD.Observable;
import GoT.DnD.Observer;
import GoT.DnD.Persistent_Layer.ReadText;
import sun.awt.image.ImageWatched;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class Board {

    //Fields
    int Level;
    private static Character[][] board;
    private static LinkedList<GameUnit> GameUnits;
    Player Hero;

    static final char EMPTY = '.';
    static final char WALL = '#';
    static final char HERO = '@';

    //Constructor
    public Board(int level, Player Hero){
        this.Level=level;
        int x=0;
        int y=0;
        List<String> boardScheme = ReadText.readAllLines("level "+this.Level+".txt");
        board = new Character[boardScheme.size()][boardScheme.get(1).length()]; //Initialize board in the required dimension
        GameUnits = BoardSchemeParser.ParseScheme(boardScheme,Hero);
        this.Hero=(Player)GameUnits.getFirst();
        GameUnits.removeFirst();
        for (String line:boardScheme) {
            for (char tile:line.toCharArray()) {
                board[x][y]=tile;
                y++;
            }
            y=0;
            x++;
        }
    }

    //Methods
    //region Methods of Board class
    public void castSpecialAbility(){
        Hero.castSpecialAbility(GameUnits);
    }
    public LinkedList<GameUnit> getGameUnits() {
        return GameUnits;
    }
    public LinkedList<GameUnit> NearbyGameUnits(GameUnit gameunit, Double radius) {
        LinkedList<GameUnit> allCreatures = this.getGameUnits();
        LinkedList<GameUnit> nearbyCreatures = new LinkedList<GameUnit>();
        for (GameUnit creature : allCreatures) {
            if (range(gameunit, creature) <= radius) {
                nearbyCreatures.addLast(creature);
            }

        }
        return nearbyCreatures;
    }
    public Double rangeToHero(GameUnit gameUnit){
        return  range(Hero,gameUnit);
    }
    public static Double range(GameUnit gameunit1, GameUnit gameunit2){
        return gameunit1.getPosition().distance(gameunit2.getPosition());
    }
    public static boolean isLegalMove(GameUnit gameUnit, int move){//maybe we need to get the gameunit that tries to preform the move
        Point p = gameUnit.getPosition();
        Point destination=null;
        Character gu = null;
        switch (move){
            case 1:
                gu = board[p.x-1][p.y];
                destination=new Point(p.x-1,p.y);
                break;
            case 2:
                gu = board[p.x+1][p.y];
                destination=new Point(p.x+1,p.y);
                break;
            case 3:
                gu = board[p.x][p.y+1];
                destination=new Point(p.x,p.y);
                break;
            case 4:
                gu = board[p.x][p.y-1];
                destination=new Point(p.x,p.y-1);
                break;
        }
        if (gu == EMPTY){
            return true;
        } else if(gu != WALL && gu != HERO) {
            return false;
        } else if (gu == HERO){
            GameUnit defender = getGameUnitByPosition(destination);
            gameUnit.meleeCombat(defender);
            if(gameUnit.getCurrHP()<=0){
                GameUnits.remove(gameUnit);
            }
            if(defender.getCurrHP()<=0){
                GameUnits.remove(gameUnit);
            }
            return true;
        } else
            return false;

    }
    public void MoveHero(int direction){
        Point prevPoint = Hero.getPosition();
        Hero.Move(direction);
        board[prevPoint.x][prevPoint.y]=EMPTY;
        board[Hero.getPosition().x][Hero.getPosition().y]=HERO;

    }
    private List<Point> getEmptyPlaces(){
        List<Point> emptySpots = new LinkedList<>();
        for(int x=0;x<board.length;x++){
            for(int y=0;y<board[x].length;y++){
                if(board[x][y]==EMPTY){
                    emptySpots.add(new Point(x,y));
                }
            }
        }
        return emptySpots;
    }
    public String BoardToDisplay(){
        String boardAsString="";
        for(Character[] line:board){
            for (Character character:line){
                if (Hero.getCurrHP()<=0 && character==HERO)
                {boardAsString=boardAsString+'X';
                }
                else{
                boardAsString=boardAsString+character;
                }

            }
            boardAsString=boardAsString.concat(System.lineSeparator());
        }
        return boardAsString;
    }

    private static GameUnit getGameUnitByPosition(Point point){
        GameUnit toReturn=null;
        for(GameUnit gu :GameUnits){
            if (gu.getPosition().equals(point)){
                toReturn=gu;
            }
        }
        return toReturn;
    }
    public void gameTick(){
        for(GameUnit c:GameUnits){
            Enemy gu= (Enemy)c;
            Point prevPoint = gu.getPosition();
            if(gu.GameUnitType().equals("Trap")){
                Trap t=(Trap)gu;
                if(t.gameTick(getEmptyPlaces())){// in case that the trap have been moved
                    board[prevPoint.x][prevPoint.y]=EMPTY;
                    if(t.getIsVisible()){
                        board[t.getPosition().x][t.getPosition().y]=t.getTile();
                    }
                    else{
                        board[t.getPosition().x][t.getPosition().y]=EMPTY;
                    }
                }
                else{ //Trap became invisible and didnt moved
                    if(t.getIsVisible()){
                        board[t.getPosition().x][t.getPosition().y]=t.getTile();
                    }
                    else{
                        board[t.getPosition().x][t.getPosition().y]=EMPTY;
                    }
                }
            }
            else {//Monster has been moved
                    gu.gameTick();
                    board[prevPoint.x][prevPoint.y]=EMPTY;
                    board[gu.getPosition().x][gu.getPosition().y]=gu.getTile();
            }
        }
    }
    //endregion

}


