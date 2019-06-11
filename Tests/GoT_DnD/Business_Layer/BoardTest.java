package GoT_DnD.Business_Layer;

import GoT_DnD.Business_Layer.GameUnits.Player;
import GoT_DnD.Business_Layer.GameUnits.Warrior;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

class BoardTest {
    //Fields:
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;
    private static final int LEFT = 4;

    private Board board=null;
    private Player hero=null;
    private String DemoLevelPath = "test_Level.txt";

    @BeforeEach
    void setUp() {
        hero = new Warrior("Jon Snow",300, 30,4,new Point(0,0),6,'@');
        board = new Board(DemoLevelPath,hero);
    }


    @Test
    void moveHero() {
        Point currLocation = new Point (board.getHero().getPosition());
        board.getHero();
        board.MoveHero(UP);
        Assertions.assertEquals(board.getHero().getPosition().x, currLocation.x-1);
        Assertions.assertEquals(board.getHero().getPosition().y,currLocation.y);

        board.MoveHero(RIGHT);
        Assertions.assertEquals(board.getHero().getPosition().x, currLocation.x-1);
        Assertions.assertEquals(board.getHero().getPosition().y,currLocation.y+1);

        board.MoveHero(LEFT);
        Assertions.assertEquals(board.getHero().getPosition().x, currLocation.x-1);
        Assertions.assertEquals(board.getHero().getPosition().y,currLocation.y);

        board.MoveHero(DOWN);
        Assertions.assertEquals(board.getHero().getPosition(),currLocation); //supposed to be the same  point
    }

    @Test
    void getEmptyPlaces() {

        //the board we built have 30 empty places
        Assertions.assertEquals(30, Board.getEmptyPlaces().size());
    }

    @Test
    void isLegalMove(){
        Assertions.assertEquals(1, Board.isLegalMove(hero, LEFT));//hitting wall at this board layout
        Assertions.assertEquals(0, Board.isLegalMove(hero, UP));//supposed to be empty
    }

}