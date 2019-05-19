package GoT.DnD.Business_Layer;

public class Mage extends Player {
    private Integer spellPower;
    private Integer manaPool;
    private Integer currMana;
    private Integer cost;
    private Integer hitTimes;
    private Integer range;

    public Mage(String name, Integer hp, Integer currHP, Integer ap, Integer dp, Integer[] position, Integer spellPower, Integer manaPool, Integer cost, Integer hitTimes, Integer range){
        super(name, hp, currHP, ap, dp, position);
        this.spellPower = spellPower;
        this.manaPool = manaPool;
        this.currMana = manaPool / 4;
        this.cost = cost;
        this.hitTimes = hitTimes;
        this.range = range;
    }

    @Override
    void levelUp() {
        if (this.isLevelUp()) {
            this.levelUpIsComing();
            manaPool = manaPool + (25 * level);
            currMana = Math.min(currMana + (manaPool / 4), manaPool);
            spellPower = spellPower + (10 * level);
        }
    }

    @Override
    void castSpecialAbility() {
       if (currMana < cost){
           //@TODO: Generate an appropriate error message.
       }
       else {
           currMana = currMana - cost;
           Integer hits = 0;
           while (hits < hitTimes /*range(enemy, player)*/){
               //@TODO: Implement while hits < hit times ∧ ∃ enemy s.t. range(enemy, player) < range do...

           }
       }
    }

    @Override
    public void gameTick() {
        currMana = Math.min(manaPool, currMana + 1);
    }
}
