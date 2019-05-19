package GoT.DnD.Business_Layer;

public abstract class GameUnit {
    protected String name;
    protected Integer hp;              //Health Pool
    protected Integer currHP;          //Current Health
    protected Integer ap;              //Attacking Points
    protected Integer dp;              //Defense Points
    protected Integer[] position;

    public GameUnit(String name, Integer hp, Integer currHP, Integer ap, Integer dp, Integer[] position) {
        this.name = name;
        this.hp = hp;
        this.currHP = currHP;
        this.ap = ap;
        this.dp = dp;
        this.position = new Integer[2];
    }

    public abstract void gameTick();

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
