package GoT.DnD.Business_Layer;

import GoT.DnD.Observable;
import GoT.DnD.Observer;

import java.awt.*;
import java.util.LinkedList;

public abstract class Player extends GameUnit implements Observable {
    private Integer level;      //Player's level
    protected LinkedList<Observer> observers;

    public Player (String name, Integer hp, Integer ap, Integer dp, Point position){
        super(name, hp, ap, dp, position,0);
        this.level = 1;
        observers=new LinkedList<>();
    }

    protected boolean isLevelUp(){
        return (getXp() >= (level * 50));
    }

    protected void levelUpIsComing(){
        if (isLevelUp()) {
            setXp(getXp() - (50 * level));
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

    public void setXP(Integer xp){
        setXp(xp);
        if (isLevelUp()){
            levelUpIsComing();
            levelUp();
        }
    }
}
