package GoT.DnD.Business_Layer;

import java.awt.*;

public class Trap extends Enemy{
    private Integer reloRange;          //Relocation range
    private Integer reloTime;           //Ticks until trap relocates
    private Integer visTime;            //Visibility time. Ticks until trap becomes invisible
    private Integer tickCount;          //# of ticks since last relocation

    public Trap(String name, Integer hp, Integer currHP, Integer ap, Integer dp, Point position, Integer xp, char tile, Integer reloRange, Integer reloTime, Integer visTime, Integer tickCount) {
        super(name, hp, currHP, ap, dp, position, xp, tile);
        this.reloRange = reloRange;
        this.reloTime = reloTime;
        this.visTime = visTime;
        this.tickCount = tickCount;
    }

    @Override
    public void gameTick() {
        if (tickCount == reloTime) {
            tickCount = 0;
            //@TODO: Find all free positions within relocation range, randomly select one and set as position
        } else {
            tickCount++;
            if (range(this, HERO) < 2){
                //@TODO: Engage in melee combat
            }
        }
        if (tickCount < visTime){
            //@TODO: Set visible
        } else {
            //@TODO: Set invisible
        }
    }
}
