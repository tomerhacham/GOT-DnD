package GoT.DnD.Business_Layer;

public abstract class GameUnit {
    private String name;
    private Integer hp;              //Health Pool
    private Integer currHP;          //Current Health
    private Integer ap;              //Attacking Points
    private Integer dp;              //Defense Points
    private Integer[] position;

    public GameUnit(String name, Integer hp, Integer currHP, Integer ap, Integer dp, Integer[] position) {
        this.name = name;
        this.hp = hp;
        this.currHP = currHP;
        this.ap = ap;
        this.dp = dp;
        this.position = new Integer[2];
    }

    //Abstract methods
    public abstract void gameTick();


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

    public Integer[] getPosition() {
        return position;
    }

    public void setPosition(Integer[] position) {
        this.position = position;
    }
}
