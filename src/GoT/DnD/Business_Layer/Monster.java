package GoT.DnD.Business_Layer;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Monster extends Enemy{
    private Integer vr;             //Vision range

    public Monster(String name, Integer hp, Integer currHP, Integer ap, Integer dp, Point position, Integer xp, char tile, Integer vr){
        super(name, hp, currHP, ap, dp, position, xp, tile);
        this.vr = vr;
    }

    @Override
    public void gameTick() {
        if range(this, HERO) { //@TODO: HERO REFERENCE
            int dx = this.getPosition().x - hero.getPoisition().x;
            int dy = this.getPosition().y - hero.getPoistion().y;
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0){
                    this.setPosition(this.getPosition().x - 1, this.getPosition().y);
                } else {
                    this.setPosition(this.getPosition().x + 1, this.getPosition().y);
                }
            } else {
                if (dy > 0) {
                    this.setPosition(this.getPosition().x, this.getPosition().y + 1);
                } else {
                    this.setPosition(this.getPosition().x, this.getPosition().y - 1);
                }
            }
        } else {
            int randomMove = ThreadLocalRandom.current().nextInt(1, 5);
            switch(randomMove) {
                case 1:     //Move Right

            }
        }
    }


    //Getter & setter
    public Integer getVr() {
        return vr;
    }

    public void setVr(Integer vr) {
        this.vr = vr;
    }
}
