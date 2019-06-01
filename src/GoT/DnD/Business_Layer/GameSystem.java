package GoT.DnD.Business_Layer;
import GoT.DnD.Observable;
import GoT.DnD.Observer;
import GoT.DnD.View;
import sun.awt.image.ImageWatched;

import javax.naming.ldap.Control;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class GameSystem implements Observer {
    //Fields
    Board board;
    Player Hero;
    LinkedList<Player> OptionsforPick;
    LinkedList<Observer> observers;
    CombatSystem combatSystem;

    //Constructors
    public GameSystem(){
        this.Hero=initialize();
        board = new Board("level 1",Hero);
        this.observers=new LinkedList<Observer>();

        //region Player Options
        OptionsforPick.add(new Warrior ("Jon Snow",300, 30,4,new Point(0,0),6));
        OptionsforPick.add(new Warrior ("The Hound",400, 20,6,new Point(0,0),4));
        OptionsforPick.add(new Mage("Melisandre",160,10,1,new Point(0,0),40,300,30,5,6));
        OptionsforPick.add(new Mage("Thoros of Myr",250,25,3,new Point(0,0),15,150,50,3,3));
        OptionsforPick.add(new Rogue("Arya Stark",150,40,2,new Point(0,0),20));
        OptionsforPick.add(new Rogue("Bronn",250,35,3,new Point(0,0),60));
        //endregion
        CombatSystem combatSystem = new CombatSystem();
        combatSystem.register(this);
    }

    //region Methods
    public void gameTick(){
        //TODO: after action of the Hero from the input
        board.gameTick();
        }
    public void castSpecialAbility(){
        board.castSpecialAbility();

    }
    //endregion

    //region Observer implement
    @Override
    public void update(Object o) {


    }
    public void update(char c){}
    public void update(String message){
        View.Display(message);
    }
    //endregion

    public Player initialize(){
        String OpenMessage="Select Player: "+System.lineSeparator();
        for (Player player:OptionsforPick){
            OpenMessage.concat(player.toString()+System.lineSeparator());
        }
        View.Display(OpenMessage);
        int PlayerSelection=0; //TODO:controller get  the input
        return OptionsforPick.get(PlayerSelection-1);
    }
}
