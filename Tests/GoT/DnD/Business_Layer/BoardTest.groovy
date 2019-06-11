package GoT.DnD.Business_Layer
import org.junit.Assert;
import org.junit.Test;

import java.awt.Point

class BoardTest extends GroovyTestCase {

    //Fields:
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;
    private static final int LEFT = 4;

    Board board=null;
    Player hero=null;
    String DemoLevelPath = "test Level.txt"


    void Before() {
        super.Before()
        hero = new Warrior ("Jon Snow",300, 30,4,new Point(0,0),6,'@');
        board = new Board(DemoLevelPath,hero);
    }

    @Test
    void testMoveHero() {
        Point currLocation = new Point (board.Hero.getPosition());

        board.MoveHero(UP);
        assertEquals(board.Hero.getPosition().x, currLocation.x-1);
        assertEquals(board.Hero.getPosition().y,currLocation.y);

        board.MoveHero(RIGHT);
        assertEquals(board.Hero.getPosition().x, currLocation.x-1);
        assertEquals(board.Hero.getPosition().y,currLocation.y+1);

        board.MoveHero(LEFT);
        assertEquals(board.Hero.getPosition().x, currLocation.x-1);
        assertEquals(board.Hero.getPosition().y,currLocation.y);

        board.MoveHero(DOWN);
        assertEquals(board.Hero.getPosition(),currLocation); //supposed to be the same  point
    }

    @Test
    void testIsLegalMove() {

    }
}
