package GoT.DnD.Business_Layer;

import java.awt.*;

public abstract class Enemy extends GameUnit {
    private Integer xp;         //Experience value
    private char tile;
    private GameUnit Hero;

    public Enemy(String name, Integer hp, Integer ap, Integer dp, Point position, Integer xp, char tile, GameUnit Hero){
        super(name, hp, ap, dp, position);
        this.Hero=Hero;
        this.xp = xp;
        this.tile = tile;
    }

    //Methods
    public double rangeToHero(){
        return this.getPosition().distance(Hero.getPosition());
    }


    //Getters & setters
    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

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
