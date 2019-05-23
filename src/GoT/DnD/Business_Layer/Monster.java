package GoT.DnD.Business_Layer;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Monster extends Enemy{
    private Integer vr;             //Vision range

    public Monster(String name, Integer hp, Integer ap, Integer dp, Point position, Integer xp, char tile, Integer vr,GameUnit Hero){
        super(name, hp, ap, dp, position, xp, tile,Hero);
        this.vr = vr;
    }

    public void gameTick() {
        if (rangeToHero() < vr) {
            int dx = this.getPosition().x - super.getHero().getPosition().x;
            int dy = this.getPosition().y - super.getHero().getPosition().y;
            if (Math.abs(dx) > Math.abs(dy)) {
                chaseDown(dx, LEFT, RIGHT);
            } else {
                chaseDown(dy, UP, DOWN);
            }
        } else {
            int randomMove = ThreadLocalRandom.current().nextInt(1, 5);
            this.Move(randomMove);
        }
    }

    //Checks if Player close enough to chase, and where to move
    private void chaseDown(int axis, int neg, int pos) {
        if (axis > 0){
            if (Board.isLegalMove(this.getPosition(), neg)) {
                this.Move(neg);
            }
        } else if (Board.isLegalMove(this.getPosition() , pos)) {
            this.Move(pos);
        }
    }

    public String GameUnitType(){return "Monster"; }
    //Getter & setter
    public Integer getVr() {
        return vr;
    }

    public void setVr(Integer vr) {
        this.vr = vr;
    }
}
