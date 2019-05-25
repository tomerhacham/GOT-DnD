package GoT.DnD.Business_Layer;

import java.util.concurrent.ThreadLocalRandom;

public class CombatSystem {
    public static void meeleCombat(GameUnit attacker, GameUnit defender){
        int attackRoll = ThreadLocalRandom.current().nextInt(0,attacker.getAp());
        int defenderRoll = ThreadLocalRandom.current().nextInt(0,defender.getDp());
        if(attackRoll-defenderRoll>0){
            defender.setCurrHP(defender.getCurrHP()-(attackRoll-defenderRoll));
            if(defender.getCurrHP()<=0){
                attacker.setXp(attacker.getXp()+defender.getXp());  //added experience point of the defender to the attacker
                //TODO: mark this GameUnit as dead, can be with flag
                //TODO: handle in case that the hero is dead to end the game' may need to use observer here
            }
        }
    }
}
