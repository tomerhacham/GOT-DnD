package GoT.DnD.Business_Layer;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Monster extends Enemy{
    private Integer vr;             //Vision range

    public Monster(String name, Integer hp, Integer ap, Integer dp, Point position, Integer xp, char tile, Integer vr,GameUnit Hero){
        super(name, hp, ap, dp, position, xp, tile,Hero);
        this.vr = vr;
    }

    @Override
    public void gameTick() {
        if range(this, HERO) { //TODO: HERO REFERENCE
            int dx = this.getPosition().x - hero.getPoisition().x;
            int dy = this.getPosition().y - hero.getPoistion().y;
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0){
                    this.Move(LEFT);
                } else {
                    this.Move(RIGHT);
                }
            } else {
                if (dy > 0) {
                    this.Move(UP);
                } else {
                    this.Move(DOWN);
                }
            }
        } else {
            int randomMove = ThreadLocalRandom.current().nextInt(1, 5);
            this.Move(randomMove);
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
