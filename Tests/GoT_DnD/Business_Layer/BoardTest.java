package GoT_DnD.Business_Layer;

import GoT_DnD.Business_Layer.GameUnits.Player;
import GoT_DnD.Business_Layer.GameUnits.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    //Fields:
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;
    private static final int LEFT = 4;

    Board board=null;
    Player hero=null;
    String DemoLevelPath = "test Level.txt";

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
        assertEquals(board.getHero().getPosition().x, currLocation.x-1);
        assertEquals(board.getHero().getPosition().y,currLocation.y);

        board.MoveHero(RIGHT);
        assertEquals(board.getHero().getPosition().x, currLocation.x-1);
        assertEquals(board.getHero().getPosition().y,currLocation.y+1);

        board.MoveHero(LEFT);
        assertEquals(board.getHero().getPosition().x, currLocation.x-1);
        assertEquals(board.getHero().getPosition().y,currLocation.y);

        board.MoveHero(DOWN);
        assertEquals(board.getHero().getPosition(),currLocation); //supposed to be the same  point
    }

    @Test
    void getEmptyPlaces() {
    }
}