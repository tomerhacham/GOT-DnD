package GoT.DnD.Business_Layer;

import java.awt.*;

public abstract class Enemy extends GameUnit {
    private char tile;
    private GameUnit Hero;

    public Enemy(String name, Integer hp, Integer ap, Integer dp, Point position, Integer xp, char tile, GameUnit Hero){
        super(name, hp, ap, dp, position,xp);
        this.Hero=Hero;
        this.tile = tile;
    }

    //Methods
    public double rangeToHero(){
        return this.getPosition().distance(Hero.getPosition());
    }

    public String toString(){
        return getName() + " Health: " + getCurrHP() + " Attack damage: " + getAp() + " Defense: " + getDp();

    }


    //Getters & setters

    public char getTile() {
        return tile;
    }

    public void setTile(char tile) {
        this.tile = tile;
    }

    public GameUnit getHero() {
        return Hero;
    }
}
