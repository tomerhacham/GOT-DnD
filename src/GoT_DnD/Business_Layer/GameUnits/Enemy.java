package GoT_DnD.Business_Layer.GameUnits;

import java.awt.*;

public abstract class Enemy extends GameUnit {
    private GameUnit Hero;

    public Enemy(String name, Integer hp, Integer ap, Integer dp, Point position, Integer xp, char tile, GameUnit Hero){
        super(name, hp, ap, dp, position,xp,tile);
        this.Hero=Hero;
    }

    //Methods
    public double rangeToHero(){
        return this.getPosition().distance(Hero.getPosition());
    }

    @Override
    public String toString(){
        return getName() + "        Health: " + getCurrHP() + "        Attack damage: " + getAp() + "        Defense: " + getDp();

    }
    public boolean isEnemy(){
        return true;
    }
    public boolean stepOn(GameUnit gu){
        if(gu.isEnemy()){
            return false;
        }
        else{
            return gu.meleeCombat(this);
        }
    }

    //Getters & setters


    public GameUnit getHero() {
        return Hero;
    }
}
