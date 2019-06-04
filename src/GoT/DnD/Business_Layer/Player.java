package GoT.DnD.Business_Layer;

import GoT.DnD.Observable;
import GoT.DnD.Observer;

import java.awt.*;
import java.util.LinkedList;

public abstract class Player extends GameUnit implements Observable {
    private Integer level;      //Player's level
    protected LinkedList<Observer> observers;

    public Player (String name, Integer hp, Integer ap, Integer dp, Point position,char tile){
        super(name, hp, ap, dp, position,0,tile);
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
    public boolean isEnemy(){
        return false;
    }

    //Abstract methods
    abstract void levelUp();
    abstract void castSpecialAbility(LinkedList<GameUnit> enemies);
    public boolean stepOn(GameUnit enemy){
        if(enemy.isEnemy()){
            return enemy.meleeCombat(this);
        }
        else{
            return false;
        }
    }
    public String GameUnitType(){return "Player"; }

    //Getters & setters
    public void setCurrHP(Integer currHP) {
        super.setCurrHP(currHP);
        if (currHP<=0){
            notifyObserver(this.getName()+" died."+System.lineSeparator()+"You Lost.");
        }
    }
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
