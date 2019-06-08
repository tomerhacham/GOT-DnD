package GoT_DnD.Business_Layer.GameUnits;

import java.awt.*;
import java.util.LinkedList;

public class Warrior extends Player {
    private Integer cooldown;
    private Integer remaining;

    public Warrior(String name, Integer hp, Integer ap, Integer dp, Point position, Integer cooldown,char tile){
        super(name, hp, ap, dp, position,tile);
        this.cooldown = cooldown;
        this.remaining = 0;
    }

    @Override
    public void levelUp(){
        String lvlUpMsg=this.getName() + " LEVEL UP!"+System.lineSeparator();
            this.levelUpIsComing();
            remaining = 0;
            setHp(getHp() + (5 * getLevel()));
            setDp(getDp() + getLevel());
        lvlUpMsg=lvlUpMsg.concat("Level up: +"+((10 * getLevel()-1)+(5 * getLevel()))+" Health, +"+(5 * getLevel()-1)+" Attack, +"+(2 * getLevel()-1+getLevel())+" Defense,");
        notifyObserver(lvlUpMsg);
    }

    @Override
    public void castSpecialAbility(LinkedList<GameUnit> enemies) {
        String message="";
        if (remaining > 0){
            message= message.concat(this.getName()+" tried to cast Heal, but there is a cool-down: "+ remaining);
        }
        else {
            remaining = cooldown;
            setCurrHP(Math.min(getCurrHP() + (2 * getDp()), getHp()));
            message= message.concat(this.getName()+" cast Heal"+System.lineSeparator());
            message=message.concat(this.toString());
        }
        notifyObserver(message);
    }

    @Override
    public boolean gameTick() {
        remaining = Math.max(0,remaining-1);
        return true;
    }

    @Override
    public String toString(){
        return getName() + "        Health: " + getCurrHP() + "        Attack damage: " + getAp() + "        Defense: " + getDp() + "        Level: " + getLevel() + "        Experience: " + getXp() + "/" + 50*getLevel() + "        Ability cooldown: " + getCooldown();
    }

    private Integer getCooldown() {
        return cooldown;
    }
}
