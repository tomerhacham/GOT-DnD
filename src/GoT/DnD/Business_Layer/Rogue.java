package GoT.DnD.Business_Layer;

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
        if (this.isLevelUp()){
            currEnergy = 100;
            setAp(getAp() + (3 * getLevel()));
        }
    }

    @Override
    void castSpecialAbility(LinkedList<GameUnit> enemies) {
        if (currEnergy < cost){
            //@TODO: Generate an appropriate error message.
        }
        else {
            currEnergy = currEnergy - cost;
            LinkedList<Enemy> nearBy = new LinkedList<>();
            for (GameUnit enemy: enemies) {
                if (getPosition().distance(enemy.getPosition()) < 2){
                    nearBy.add((Enemy)enemy);
                }
            }
            for (Enemy enemy: nearBy){
                //TODO: Engage combat.
            }
        }
    }

    @Override
    public void gameTick() {
        currEnergy = Math.min(currEnergy + 10, 100);
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
}
