package GoT_DnD.Business_Layer.GameUnits;

import GoT_DnD.Business_Layer.GameSystem;

import java.awt.*;
import java.util.LinkedList;

public class Mage extends Player {
    private Integer spellPower;
    private Integer manaPool;
    private Integer currMana;
    private Integer cost;
    private Integer hitTimes;
    private Integer range;

    public Mage(String name, Integer hp, Integer ap, Integer dp, Point position, Integer spellPower, Integer manaPool, Integer cost, Integer hitTimes, Integer range,char tile){
        super(name, hp, ap, dp, position,tile);
        this.spellPower = spellPower;
        this.manaPool = manaPool;
        this.currMana = manaPool / 4;
        this.cost = cost;
        this.hitTimes = hitTimes;
        this.range = range;
    }

    @Override
    public void levelUp() {
        String lvlUpMsg=this.getName() + " LEVEL UP!"+System.lineSeparator();
            this.levelUpIsComing();
            manaPool = manaPool + (25 * getLevel());
            currMana = Math.min(currMana + (manaPool / 4), manaPool);
            spellPower = spellPower + (10 * getLevel());
        lvlUpMsg=lvlUpMsg.concat("Level up: +"+(10 * getLevel()-1)+" Health, +"+(5 * getLevel()-1)+" Attack, +"+(2 * getLevel()-1)+" Defense, +"+(25 * getLevel())+" maximum mana, "+(10 * getLevel())+" spell power");
        notifyObserver(lvlUpMsg);
    }

    @Override
    public void castSpecialAbility(LinkedList<GameUnit> enemies1) {
        String message="";
        LinkedList<GameUnit> enemies=new LinkedList<>(enemies1);
        enemies.removeFirst();              //Remove hero himself from list
       if (currMana < cost){
           message=message.concat(this.getName()+" tried to cast Blizzard, but there was not enough mana");
           notifyObserver(message);
       }
       else {
           LinkedList<Enemy> nearBy = new LinkedList<>();
           for (GameUnit enemy: enemies) {
               if (getPosition().distance(enemy.getPosition()) <= range){
                   nearBy.add((Enemy)enemy);
               }
           }
           message=message.concat(this.getName()+" cast Blizzard");
           notifyObserver(message);
           currMana = currMana - cost;
           Integer hits = 0;
           while (hits < hitTimes && !nearBy.isEmpty()){
               int randomSelection = GameSystem.randomGenerator.nextInt(nearBy.size()-1);
               if(randomSelection<nearBy.size()) {
                   Enemy victim = nearBy.get(randomSelection);
                   meleeCombat(victim);
               }
               hits++;
           }
       }
    }

    @Override
    public boolean gameTick() {
        currMana = Math.min(manaPool, currMana + 1);
        return true;
    }


    //Getters & setters
    private Integer getSpellPower() {
        return spellPower;
    }

    private Integer getManaPool() {
        return manaPool;
    }

    private Integer getCurrMana() {
        return currMana;
    }

    @Override
    public String toString(){
        return this.getName()+"        Health:  "+getCurrHP()+"        Attack damage: "+getAp()+"        Defense: "+getDp()+"        Level: "+getLevel()+ "        Experience: "+getXp()+"/"+(50*getLevel())+"        SpellPower: "+getSpellPower()+"        Mana: "+getCurrMana()+"/"+getManaPool();
    }
}
