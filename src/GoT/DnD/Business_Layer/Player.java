package GoT.DnD.Business_Layer;

public abstract class Player extends GameUnit {
    private Integer level;      //Player's level
    private Integer xp;         //Experience


    public Player (String name, Integer hp, Integer currHP, Integer ap, Integer dp, Integer[] position){
        super(name, hp, currHP, ap, dp, position);
        this.level = 1;
        this.xp = 0;
    }

    protected boolean isLevelUp(){
        return (xp >= (level * 50));
    }

    protected void levelUpIsComing(){
        if (isLevelUp()) {
            xp = xp - (50 * level);
            level++;
            setHp(getHp() + (10 * level));
            setCurrHP(getHp());
            setAp(getAp() + (5 * level));
            setDp(getDp() + (2 * level));
        }
    }

    //Abstract methods
    abstract void levelUp();
    abstract void castSpecialAbility();


    //Getters & setters
    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
