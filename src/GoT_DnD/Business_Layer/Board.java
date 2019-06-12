package GoT_DnD.Business_Layer;

import GoT_DnD.Business_Layer.GameUnits.GameUnit;
import GoT_DnD.Business_Layer.GameUnits.Player;
import GoT_DnD.Persistent_Layer.ReadText;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Board {

    //Fields
    private int Level;
    private static Character[][] board;
    private static LinkedList<GameUnit> GameUnits;

    private Player Hero;

    private static final Character EMPTY = '.';
    private static final Character WALL = '#';
    private static final Character HERO = '@';

    //Constructor
    public Board(String filename, Player Hero) {
        this.Level = filename.charAt(filename.length()-5)-'0';
        int x = 0;
        int y = 0;
        List<String> boardScheme = ReadText.readAllLines(filename);
        board = new Character[boardScheme.size()][boardScheme.get(1).length()]; //Initialize board in the required dimension
        GameUnits = BoardSchemeParser.ParseScheme(boardScheme, Hero);
        this.Hero = (Player) GameUnits.getFirst();
        for (String line : boardScheme) {
            for (char tile : line.toCharArray()) {
                board[x][y] = tile;
                y++;
            }
            y = 0;
            x++;
        }
    }

    //Methods
    //region Methods of Board class
    public void MoveHero(int direction) {
        Point prevPoint = new Point(Hero.getPosition());
        int legalSituation = Board.isLegalMove(Hero, direction);
        if (legalSituation == 0) {
            Hero.Move(direction);
            board[prevPoint.x][prevPoint.y] = EMPTY;
            board[Hero.getPosition().x][Hero.getPosition().y] = HERO;
        } else if (legalSituation == 2) {
               GameUnit gameUnitAtDes = getGameUnitByPosition(getPointByDirection(Hero.getPosition(),direction));
               if(gameUnitAtDes.stepOn(Hero)){
                   Hero.Move(direction);
                   board[prevPoint.x][prevPoint.y] = EMPTY;
                   board[Hero.getPosition().x][Hero.getPosition().y] = HERO;
               }
        }
    }

    public void gameTick() {
        for(int i=1;i<GameUnits.size();i++) {
            GameUnit gu = GameUnits.get(i);
            if (gu.getCurrHP() > 0) {
                Point prevPoint = new Point(gu.getPosition());
                if (gu.gameTick()) {
                    if (gu.isVisible()) {
                        board[prevPoint.x][prevPoint.y] = EMPTY;
                        board[gu.getPosition().x][gu.getPosition().y] = gu.getTile();
                    } else {
                        board[prevPoint.x][prevPoint.y] = EMPTY;
                        board[gu.getPosition().x][gu.getPosition().y] = EMPTY;
                    }
                }
            }
            UpdateAliveGameUnits();
        }
        if(Hero.isVisible()){
            board[Hero.getPosition().x][Hero.getPosition().y]=Hero.getTile();}
        else{
            board[Hero.getPosition().x][Hero.getPosition().y]='X';
            }
        UpdateAliveGameUnits();
    }

    public void castSpecialAbility() {
        Hero.castSpecialAbility(GameUnits);
    }

    public static int isLegalMove(GameUnit gameUnit, int move) {//maybe we need to get the gameunit that tries to preform the move
        Point currLocation = new Point(gameUnit.getPosition());
        Point destination = getPointByDirection(gameUnit.getPosition(),move);
        boolean isLegal = false;
        if (board[destination.x][destination.y] == EMPTY) //Empty slot
        {
            return 0;
        } else if (board[destination.x][destination.y] == WALL) //Wall slot
        {
            return 1;
        }
        else{ //Gameunit to be found later
            return 2;
        }

    }

    public LinkedList<GameUnit> getGameUnits() {
        return GameUnits;
    }

    public String BoardToDisplay() {
        String boardAsString = "";
        for (Character[] line : board) {
            for (Character character : line) {
                if (Hero.getCurrHP() <= 0 && character == HERO) {
                    boardAsString = boardAsString + 'X';
                } else {
                    boardAsString = boardAsString + character;
                }

            }
            boardAsString = boardAsString.concat(System.lineSeparator());
        }
        return boardAsString;
    }

    public static List<Point> getEmptyPlaces() {
        List<Point> emptySpots = new LinkedList<>();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] == EMPTY) {
                    emptySpots.add(new Point(x, y));
                }
            }
        }
        return emptySpots;
    }

    public static GameUnit getGameUnitByPosition(Point point) {
        GameUnit toReturn = null;
        for (GameUnit gu : GameUnits) {
            if (gu.getPosition().equals(point)) {
                toReturn = gu;
            }
        }
        return toReturn;
    }

    private void UpdateAliveGameUnits() {
        LinkedList<GameUnit> DeadGameUnits = new LinkedList<>();
        for (GameUnit gu : GameUnits) {
            if (gu.getCurrHP() <= 0) {
                DeadGameUnits.add(gu);
            }
        }
        for (GameUnit gu : DeadGameUnits) {
            GameUnits.remove(gu);
        }
    }

    public static Point getPointByDirection(Point curr, int direction)
    {
        Point destination=null;
        switch(direction){
            case 1:destination=new Point(curr.x-1,curr.y);
                break;
            case 2:destination=new Point(curr.x+1,curr.y);
                break;
            case 3:destination=new Point(curr.x,curr.y+1);
                break;
            case 4:destination=new Point(curr.x,curr.y-1);
                break;
            }
    return destination;
    }
    public Player getHero() {
        return Hero;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level){
        this.Level = level;
    }

    //endregion
}





