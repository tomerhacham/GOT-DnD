package GoT.DnD.Business_Layer;

public abstract class GameUnit {
    public String name;
    public Integer hp;              //Health Pool
    public Integer currHP;          //Current Health
    public Integer ap;              //Attacking Points
    public Integer dp;              //Defense Points
    public Integer[] position;

    public GameUnit(String name, Integer hp, Integer currHP, Integer ap, Integer dp, Integer[] position) {
        this.name = name;
        this.hp = hp;
        this.currHP = currHP;
        this.ap = ap;
        this.dp = dp;
        this.position = new Integer[2];
    }

    public Integer getCurrHP() {
        return currHP;
    }

    public void setCurrHP(Integer currHP) {
        this.currHP = currHP;
    }

    public Integer[] getPosition() {
        return position;
    }

    public void setPosition(Integer[] position) {
        this.position = position;
    }
}
