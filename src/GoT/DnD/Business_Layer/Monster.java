package GoT.DnD.Business_Layer;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Monster extends Enemy{
    private Integer vr;             //Vision range

    public Monster(String name, Integer hp, Integer ap, Integer dp, Point position, Integer xp, char tile, Integer vr,GameUnit Hero){
        super(name, hp, ap, dp, position, xp, tile,Hero);
        this.vr = vr;
    }

    public boolean gameTick() {
        boolean isMoved=false;
        if (rangeToHero() < vr) {
            int dx = this.getPosition().x - super.getHero().getPosition().x;
            int dy = this.getPosition().y - super.getHero().getPosition().y;
            if (Math.abs(dx) > Math.abs(dy)) {
                isMoved = chaseDown(dx, LEFT, RIGHT);
            } else {
                isMoved = chaseDown(dy, UP, DOWN);
            }
        } else {
            int randomMove = ThreadLocalRandom.current().nextInt(1, 5);
            if(Board.isLegalMove(this,randomMove)){
                this.Move(randomMove);
                isMoved=true;
            }
        }
        return isMoved;
    }

    //Checks if Player close enough to chase, and where to move
    private boolean chaseDown(int axis, int neg, int pos) {
        boolean isMoved=false;
        if (axis > 0){
            if (Board.isLegalMove(this, neg)) {
                this.Move(neg);
                isMoved=true;
            }
        } else if (Board.isLegalMove(this , pos)) {
            this.Move(pos);
            isMoved=true;
        }
        return isMoved;
    }

    //Getter & setter
    public Integer getVr() {
        return vr;
    }

    public void setVr(Integer vr) {
        this.vr = vr;
    }

    public String GameUnitType(){return "Monster"; }
}
