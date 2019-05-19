package GoT.DnD.Business_Layer;

public abstract class Player extends GameUnit {
    private Integer level;      //Player's level
    private Integer xp;         //Experience

    private Player (String name, Integer hp, Integer currHP, Integer ap, Integer dp, Integer[] position, Integer xp, Integer level){
        super(name, hp, currHP, ap, dp, position);
        this.level = 1;
        this.xp = 0;
    }

    private boolean isLevelUp(){
        return (xp >= (level * 50));
    }

    private void levelUpIsComing(){
        if (isLevelUp()) {
            xp = xp - (50 * level);
            level++;
            hp = hp + (10 * level);
            currHP = hp;
            ap = ap + (5 * level);
            dp = dp + (2 * level);
        }
    }

    abstract void castSpecialAbility();


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
