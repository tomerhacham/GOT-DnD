package GoT.DnD.Business_Layer;

public class Warrior extends Player {
    private Integer cooldown;
    private Integer remaining;

    public Warrior(String name, Integer hp, Integer currHP, Integer ap, Integer dp, Integer[] position, Integer xp, Integer level, Integer cooldown){
        super(name, hp, currHP, ap, dp, position);
        this.cooldown = cooldown;
        this.remaining = 0;
    }

    private void levelUp(){
        if (this.isLevelUp()) {
            this.levelUpIsComing();
            remaining = 0;
            hp = hp + (5 * level);
            dp = dp + level;
        }
    }
    
    @Override
    void castSpecialAbility() {
        if (remaining > 0){
            //@TODO generate an appropriate error message.
        }
        currHP = currHP + (2 * dp);
        if (currHP > hp){
            currHP = hp;
        }
    }

    @Override
    public void gameTick() {
        remaining = remaining - 1;
    }
}
