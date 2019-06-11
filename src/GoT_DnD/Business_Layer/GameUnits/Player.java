package GoT_DnD.Business_Layer.GameUnits;

import GoT_DnD.Observable;
import GoT_DnD.Observer;

import java.awt.*;
import java.util.LinkedList;

public abstract class Player extends GameUnit implements Observable {
    private Integer level;      //Player's level
    private LinkedList<Observer> observers;

    public Player(String name, Integer hp, Integer ap, Integer dp, Point position, char tile) {
        super(name, hp, ap, dp, position, 0, tile);
        this.level = 1;
        observers = new LinkedList<>();
    }

    //Abstract methods
    public abstract void levelUp();
    public abstract void castSpecialAbility(LinkedList<GameUnit> enemies);

    //Methods
    void levelUpIsComing() {
        super.setXp(getXp() - (50 * level));
        level++;
        setHp(getHp() + (10 * level));
        setCurrHP(getHp());
        setAp(getAp() + (5 * level));
        setDp(getDp() + (2 * level));
    }

    public boolean isEnemy() {
        return false;
    }

    public boolean stepOn(GameUnit enemy) {
        if (enemy.isEnemy()) {
            return enemy.meleeCombat(this);
        } else {
            return false;
        }
    }

    //Getters & setters
    private boolean isLevelUp() {
        return (getXp() >= (level * 50));
    }

    public void setCurrHP(Integer currHP) {
        super.setCurrHP(currHP);
        if (currHP <= 0) {
            notifyObserver(this.getName() + " died." + System.lineSeparator() + "You Lost.");
        }
    }

    public Integer getLevel() {
        return level;
    }

    public void setXp(Integer xp) {
        super.setXp(xp);
        if (isLevelUp()) {
            levelUpIsComing();
            levelUp();
        }
    }

    //region Observable implement
    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(Object message) {
        for (Observer obs:observers){
            obs.update((String)message);
        }

    }
    //endregion

}
