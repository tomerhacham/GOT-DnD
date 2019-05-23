package GoT.DnD.Business_Layer;

import com.sun.corba.se.impl.oa.poa.POAImpl;

import java.awt.*;

public class Warrior extends Player {
    private Integer cooldown;
    private Integer remaining;

    public Warrior(String name, Integer hp, Integer ap, Integer dp, Point position, Integer cooldown){
        super(name, hp, ap, dp, position);
        this.cooldown = cooldown;
        this.remaining = 0;
    }

    @Override
    void levelUp(){
        if (this.isLevelUp()) {
            this.levelUpIsComing();
            remaining = 0;
            setHp(getHp() + (5 * getLevel()));
            setDp(getDp() + getLevel());
        }
    }
    
    @Override
    void castSpecialAbility() {
        if (remaining > 0){
            //@TODO: Generate an appropriate error message.
        }
        else {
            remaining = cooldown;
            setCurrHP(Math.min(getCurrHP() + (2 * getDp()), getHp()));
        }
    }

    @Override
    public void gameTick() {
        remaining = remaining - 1;
    }
}
