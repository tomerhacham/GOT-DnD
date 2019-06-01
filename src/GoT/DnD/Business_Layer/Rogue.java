package GoT.DnD.Business_Layer;

import GoT.DnD.Observer;

import java.awt.*;
import java.util.LinkedList;

public class Rogue extends Player {
    private Integer cost;
    private Integer currEnergy;

    public Rogue(String name, Integer hp, Integer ap, Integer dp, Point position, Integer cost){
        super(name, hp, ap, dp, position);
        this.cost = cost;
        this.currEnergy = 100;
    }

    @Override
    void levelUp() {
        String lvlUpMsg="";
            currEnergy = 100;
            setAp(getAp() + (3 * getLevel()));
        lvlUpMsg=lvlUpMsg.concat("Level up: +"+(10 * getLevel()-1)+" Health, +"+((5 * getLevel()-1)+(3 * getLevel()))+" Attack, +"+(2 * getLevel()-1+getLevel())+" Defense,");
        notifyObserver(lvlUpMsg);
    }

    @Override
    void castSpecialAbility(LinkedList<GameUnit> enemies) {
        String message="";
        if (currEnergy < cost){
            message= message.concat(this.getName()+" tried to cast Fan of Knives, but there was not enough energy");
            notifyObserver(message);
        }
        else {
            currEnergy = currEnergy - cost;
            LinkedList<Enemy> nearBy = new LinkedList<>();
            for (GameUnit enemy: enemies) {
                if (getPosition().distance(enemy.getPosition()) < 2){
                    nearBy.add((Enemy)enemy);
                }
            }
            message= message.concat(this.getName()+" cast Fan of Knives");
            notifyObserver(message);
            for (Enemy enemy: nearBy){
                meleeCombat(enemy);
            }
        }
    }

    @Override
    public boolean gameTick() {
        currEnergy = Math.min(currEnergy + 10, 100);
        return true;
    }

    //Getters & setters
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCurrEnergy() {
        return currEnergy;
    }

    public void setCurrEnergy(Integer currEnergy) {
        this.currEnergy = currEnergy;
    }

    @Override
    public String toString(){
        return this.getName()+" Health:  "+getCurrHP()+" Attack damage: "+getAp()+" Defense: "+getDp()+" Level: "+getLevel()+ " Experience: "+getXp()+"/"+(50*getLevel())+" Energy: "+getCurrEnergy()+"/"+100;
    }
    //region Observable implement
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
