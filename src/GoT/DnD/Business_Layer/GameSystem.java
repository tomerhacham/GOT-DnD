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
    private Board board;
    private Player Hero;
    private LinkedList<Player> OptionsforPick;
    private LinkedList<Observer> observers;
    private CombatSystem combatSystem;

    //Constructors
    public GameSystem(){
        this.Hero=initialize();
        board = new Board(1,Hero);
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
        if(board.getGameUnits().size()==0 && Hero.getCurrHP()>0) {//all the monster are dead
            if(board.Level<4){
                this.LoadLevel();
            }
            else{
                update("Game is finished. You won!");
            }
        }
        update(board.BoardToDisplay());
        if(Hero.getCurrHP()<=0){
            System.exit(0);
            }
        }

    public void castSpecialAbility(){
        board.castSpecialAbility();
    }

    private void LoadLevel(){
        this.board= new Board(board.Level+1,Hero);

    }
    //region Observer implement
    @Override
    public void update(Object message) {
        View.Display((String)message);
    }
    //endregion

    public Player initialize(){
        String OpenMessage="Select Player: "+System.lineSeparator();
        for (Player player:OptionsforPick){
            OpenMessage.concat(player.toString()+System.lineSeparator());
        }
        View.Display(OpenMessage);
        int PlayerSelection=0; //TODO:controller get  the input
        View.Display("You selected: "+System.lineSeparator()+OptionsforPick.get(PlayerSelection-1).toString());
        View.Display("Use w/s/a/d to move."+System.lineSeparator()+"Use e for special ability or q to pass.");
        return OptionsforPick.get(PlayerSelection-1);
    }
}
