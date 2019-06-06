package GoT.DnD.Business_Layer;

import GoT.DnD.Observable;
import GoT.DnD.Observer;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

class CombatSystem implements Observable {
    //Fields:
    private static LinkedList<Observer> observers=new LinkedList<>();

    /**
     * if attacker wins the function will return True; for indicate that the move can be done
     * else, the function will return false and the attack has been die therefore the move cant be complete
     * @param attacker
     * @param defender
     * @return
     */
    public static boolean meleeCombat(GameUnit attacker, GameUnit defender){
        boolean isDead=false;
        String message="";
        message=message.concat(attacker.getName()+" engaged in battle with "+defender.getName()+":"+System.lineSeparator());
        message=message.concat(attacker.toString()+System.lineSeparator());
        message=message.concat(defender.toString()+System.lineSeparator());
        int attackRoll =  GameSystem.randomGenerator.nextInt(attacker.getAp());
        int defenderRoll =   GameSystem.randomGenerator.nextInt(defender.getDp());
        int dif = attackRoll-defenderRoll;
        message=message.concat(attacker.getName()+" rolled "+attackRoll+" attack points."+System.lineSeparator());
        message=message.concat(defender.getName()+" rolled "+defenderRoll+" defense points."+System.lineSeparator());
        if(dif>0) {
            message=message.concat(attacker.getName()+" hits "+defender.getName()+" for "+dif+" damage."+System.lineSeparator());
            defender.setCurrHP(defender.getCurrHP() - dif);
            if (defender.getCurrHP() <= 0) {
                isDead=true;
                defender.setVisible(false);
                attacker.setXp(attacker.getXp() + defender.getXp());  //added experience point of the defender to the attacker
                message=message.concat(defender.getName()+" died. "+attacker.getName()+" gained "+defender.getXp()+" experience!"+System.lineSeparator());
            }
        }

        else{
            message=message.concat(attacker.getName()+" hits "+defender.getName()+" for "+0+" damage."+System.lineSeparator());
        }
        message=message.concat(defender.toString()+System.lineSeparator());
        for(Observer obs:observers){
            obs.update((String)message);
            }
            return isDead;
    }
    //region Observable implement
    @Override
    public void register(Observer newObserver){
        observers.add(newObserver);

    }
    @Override

    public void unregister(Observer observer){
        observers.remove(observer);

    }
    @Override
    public void notifyObserver(Object message) {
    }
    //endregion
}
