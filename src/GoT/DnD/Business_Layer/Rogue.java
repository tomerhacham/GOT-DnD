package GoT.DnD.Business_Layer;

public class Rogue extends Player {
    private Integer cost;
    private Integer currEnergy;

    public Rogue(String name, Integer hp, Integer currHP, Integer ap, Integer dp, Integer[] position, Integer cost){
        super(name, hp, currHP, ap, dp, position);
        this.cost = cost;
        this.currEnergy = 100;
    }

    @Override
    void levelUp() {
        if (this.isLevelUp()){
            currEnergy = 100;
            setAp(getAp() + (3 * getLevel()));
        }
    }

    @Override
    void castSpecialAbility() {
        if (currEnergy < cost){
            //@TODO: Generate an appropriate error message.
        }
        else {
            currEnergy = currEnergy - cost;
            //@TODO: Implement For each enemy within range < 2, attempt to deal damage...
        }
    }

    @Override
    public void gameTick() {
        currEnergy = Math.min(currEnergy + 10, 100);
    }

    //Getters & setters
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCurrEnergy() {
        return currEnergy;
    }

    public void setCurrEnergy(Integer currEnergy) {
        this.currEnergy = currEnergy;
    }
}
