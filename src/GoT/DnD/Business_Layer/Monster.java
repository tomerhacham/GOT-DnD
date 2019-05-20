package GoT.DnD.Business_Layer;

public class Monster extends Enemy{
    private Integer vr;             //Vision range

    public Monster(String name, Integer hp, Integer currHP, Integer ap, Integer dp, Integer[] position, Integer xp, char tile, Integer vr){
        super(name, hp, currHP, ap, dp, position, xp, tile);
        this.vr = vr;
    }

    @Override
    public void gameTick() {
        if range(this, HERO) { //@TODO: HERO REFERENCE

        }
    }

    //Getter & setter
    public Integer getVr() {
        return vr;
    }

    public void setVr(Integer vr) {
        this.vr = vr;
    }
}
