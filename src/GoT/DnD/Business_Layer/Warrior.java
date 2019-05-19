package GoT.DnD.Business_Layer;

public class Warrior extends Player {
    private Integer cooldown;
    private Integer remaining;

    public Warrior(String name, Integer hp, Integer currHP, Integer ap, Integer dp, Integer[] position, Integer cooldown){
        super(name, hp, currHP, ap, dp, position);
        this.cooldown = cooldown;
        this.remaining = 0;
    }

    @Override
    void levelUp(){
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
            //@TODO: Generate an appropriate error message.
        }
        else {
            remaining = cooldown;
            currHP = Math.min(currHP + (2 * dp), hp);
        }
    }

    @Override
    public void gameTick() {
        remaining = remaining - 1;
    }
}
