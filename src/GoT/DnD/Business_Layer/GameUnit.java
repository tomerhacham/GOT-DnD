package GoT.DnD.Business_Layer;

import
import java.awt.*;

public abstract class GameUnit {
    private String name;
    private Integer hp;              //Health Pool
    private Integer currHP;          //Current Health
    private Integer ap;              //Attacking Points
    private Integer dp;              //Defense Points
    private Point position;          //Position (x,y) on 2D board

    static final int UP = 1;
    static final int DOWN = 2;
    static final int RIGHT = 3;
    static final int LEFT = 4;

    public GameUnit(String name, Integer hp, Integer ap, Integer dp, Point position) {
        this.name = name;
        this.hp = hp;
        currHP = hp;
        this.ap = ap;
        this.dp = dp;
        this.position = position;
    }

    //Abstract methods
    abstract void gameTick();
    public abstract String GameUnitType();

    //Methods
    public void Move(int direction){
        switch (direction){
            case 1:
                this.setPosition(this.getPosition().x, this.getPosition().y + 1);
                break;
            case 2:
                this.setPosition(this.getPosition().x, this.getPosition().y - 1);
                break;
            case 3:
                this.setPosition(this.getPosition().x + 1, this.getPosition().y);
                break;
            case 4:
                this.setPosition(this.getPosition().x - 1, this.getPosition().y);
                break;
        }
    }

    //Getters & setters
    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAp() {
        return ap;
    }

    public void setAp(Integer ap) {
        this.ap = ap;
    }

    public Integer getDp() {
        return dp;
    }

    public void setDp(Integer dp) {
        this.dp = dp;
    }

    public Integer getCurrHP() {
        return currHP;
    }

    public void setCurrHP(Integer currHP) {
        this.currHP = currHP;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        position.setLocation(x, y);
    }

}
