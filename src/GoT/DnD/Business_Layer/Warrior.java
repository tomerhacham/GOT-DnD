package GoT.DnD.Business_Layer;

import java.awt.*;
import java.util.LinkedList;

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
    void castSpecialAbility(LinkedList<GameUnit> enemies) {
        if (remaining > 0){
            //TODO: Generate an appropriate error message.
            // Example: "The Hound tried to cast Heal, but there is a cool-down: 3"
        }
        else {
            remaining = cooldown;
            setCurrHP(Math.min(getCurrHP() + (2 * getDp()), getHp()));
        }
    }

    @Override
    public boolean gameTick() {
        remaining = remaining - 1;
        return true;
    }
}
