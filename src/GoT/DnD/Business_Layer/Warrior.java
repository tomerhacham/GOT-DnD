package GoT.DnD.Business_Layer;

import GoT.DnD.Observer;

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
        String lvlUpMsg="";
            this.levelUpIsComing();
            remaining = 0;
            setHp(getHp() + (5 * getLevel()));
            setDp(getDp() + getLevel());
        lvlUpMsg=lvlUpMsg.concat("Level up: +"+((10 * getLevel()-1)+(5 * getLevel()))+" Health, +"+(5 * getLevel()-1)+" Attack, +"+(2 * getLevel()-1+getLevel())+" Defense,");
        notifyObserver(lvlUpMsg);
    }

    @Override
    void castSpecialAbility(LinkedList<GameUnit> enemies) {
        String message="";
        if (remaining > 0){
            message= message.concat(this.getName()+" tried to cast Heal, but there is a cool-down: "+ remaining);
        }
        else {
            remaining = cooldown;
            setCurrHP(Math.min(getCurrHP() + (2 * getDp()), getHp()));
            message= message.concat(this.getName()+" cast Heal");
        }
        notifyObserver(message);
    }

    @Override
    public boolean gameTick() {
        remaining = remaining - 1;
        return true;
    }

    @Override
    public String toString(){
        return getName() + " Health: " + getCurrHP() + " Attack damage: " + getAp() + " Defense: " + getDp() + " Level: " + getLevel() + " Experience: " + getXp() + "/" + 50*getLevel() + " Ability cooldown: " + getCooldown();
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

    public Integer getCooldown() {
        return cooldown;
    }
}
