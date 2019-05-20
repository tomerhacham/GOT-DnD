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
            setHp(getHp() + (5 * getLevel()));
            setDp(getDp() + getLevel());
        }
    }
    
    @Override
    void castSpecialAbility() {
        if (remaining > 0){
            //@TODO: Generate an appropriate error message.
        }
        else {
            remaining = cooldown;
            setCurrHP(Math.min(getCurrHP() + (2 * getDp()), getHp()));
        }
    }

    @Override
    public void gameTick() {
        remaining = remaining - 1;
    }
}
