package GoT.DnD.Business_Layer;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Trap extends Enemy{

    private Integer reloRange;          //Relocation range
    private Integer reloTime;           //Ticks until trap relocates
    private Integer visTime;            //Visibility time. Ticks until trap becomes invisible
    private Integer tickCount;          //# of ticks since last relocation
    private boolean isVisible;

    public Trap(String name, Integer hp, Integer ap, Integer dp, Point position, Integer xp, char tile, Integer reloRange, Integer reloTime, Integer visTime,GameUnit Hero) {
        super(name, hp, ap, dp, position, xp, tile, Hero);
        this.reloRange = reloRange;
        this.reloTime = reloTime;
        this.visTime = visTime;
        tickCount = 0;
        isVisible = true;
    }

    public boolean gameTick(){
        return true;
    }

    public boolean gameTick(List<Point> emptyPlaces) {
        boolean isMoved=false;
        List<Point> rangePlaces = new LinkedList<>();
        for (Point p: emptyPlaces){
            if (getPosition().distance(p) <= reloRange){
                rangePlaces.add(p);
            }
        }
        if (tickCount.equals(reloTime)) {
            tickCount = 0;
            setPosition(rangePlaces.get(ThreadLocalRandom.current().nextInt(0, rangePlaces.size())));
            isMoved=true;
        } else {
            tickCount++;
            if (rangeToHero() < 2){
                meleeCombat(getHero());
            }
        }
        if (tickCount < visTime){
            isVisible = true;

        } else {
            isVisible = false;
        }
        return  isMoved;
    }

    //Getters & setters
    public boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisable(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public String GameUnitType(){return "Trap"; }
}
