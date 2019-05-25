package GoT.DnD.Business_Layer;

import java.awt.*;
import java.util.LinkedList;

public abstract class Player extends GameUnit {
    private Integer level;      //Player's level
    private Integer xp;         //Experience


    public Player (String name, Integer hp, Integer ap, Integer dp, Point position){
        super(name, hp, ap, dp, position,0);
        this.level = 1;
    }

    protected boolean isLevelUp(){
        return (xp >= (level * 50));
    }

    protected void levelUpIsComing(){
        if (isLevelUp()) {
            xp = xp - (50 * level);
            level++;
            setHp(getHp() + (10 * level));
            setCurrHP(getHp());
            setAp(getAp() + (5 * level));
            setDp(getDp() + (2 * level));
        }
    }

    //Abstract methods
    abstract void levelUp();
    abstract void castSpecialAbility(LinkedList<GameUnit> enemies);

    public String GameUnitType(){return "Player"; }

    //Getters & setters
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
