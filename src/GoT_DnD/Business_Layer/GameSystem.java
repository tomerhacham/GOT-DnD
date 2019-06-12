package GoT_DnD.Business_Layer;
import GoT_DnD.*;
import GoT_DnD.Business_Layer.GameUnits.Mage;
import GoT_DnD.Business_Layer.GameUnits.Player;
import GoT_DnD.Business_Layer.GameUnits.Rogue;
import GoT_DnD.Business_Layer.GameUnits.Warrior;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class GameSystem implements Observer {
    //Fields
    private Board board;
    private Player Hero;
    private LinkedList<Player> OptionsforPick;
    private static ActionReader actionReader;
    public static RandomGenerator randomGenerator;
    public List<String> levelNames;

    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;
    private static final int LEFT = 4;


    //Constructors
    public GameSystem(boolean deterministicMode,List<String> levelNames){

        //region Player Options
        OptionsforPick=new LinkedList<>();
        OptionsforPick.add(new Warrior("Jon Snow",300, 30,4,new Point(0,0),6,'@'));
        OptionsforPick.add(new Warrior ("The Hound",400, 20,6,new Point(0,0),4,'@'));
        OptionsforPick.add(new Mage("Melisandre",160,10,1,new Point(0,0),40,300,30,5,6,'@'));
        OptionsforPick.add(new Mage("Thoros of Myr",250,25,3,new Point(0,0),15,150,50,3,3,'@'));
        OptionsforPick.add(new Rogue("Arya Stark",150,40,2,new Point(0,0),20,'@'));
        OptionsforPick.add(new Rogue("Bronn",250,35,3,new Point(0,0),60,'@'));
        //endregion
        this.levelNames=levelNames;
        actionReader=new ActionReader(deterministicMode);
        randomGenerator=new RandomGenerator(deterministicMode);
        this.Hero=initialize(deterministicMode);
        this.Hero.register(this);
        board = new Board(levelNames.get(0),Hero);
        CombatSystem combatSystem = new CombatSystem();
        combatSystem.register(this);
    }
    //region Methods
    private void gameTick(){
        Hero.gameTick();
            String action = actionReader.nextAction();
            switch (action) {
                case "q":
                    break;
                case "e":
                    castSpecialAbility();
                    break;
                case "w":
                    board.MoveHero(UP);
                    break;
                case "s":
                    board.MoveHero(DOWN);
                    break;
                case "d":
                    board.MoveHero(RIGHT);
                    break;
                case "a":
                    board.MoveHero(LEFT);
                    break;
            }
            board.gameTick();
            if (board.getGameUnits().size() == 1 && Hero.getCurrHP() > 0) {//all the monster are dead
                if (board.getLevel() < levelNames.size()) {
                    update("Good Job, get ready for the next mission!");
                    this.LoadLevel();
                } else {
                    update("Game is finished. You won!");
                    System.exit(0);
                }
            }
            update(board.BoardToDisplay());
    }

    private void castSpecialAbility(){
        board.castSpecialAbility();
    }

    private void LoadLevel(){
     //   board.setLevel(board.getLevel() + 1);
        this.board= new Board(levelNames.get(board.getLevel()),Hero);

    }
    //region Observer implement
    @Override
    public void update(Object message) {
        View.Display((String)message);
    }
    //endregion

    private Player initialize(boolean deterministicMode){
        int PlayerSelection=0;
        String OpenMessage="Select Player: "+System.lineSeparator();
        int index=1;
        for (Player player:OptionsforPick){
            OpenMessage=OpenMessage.concat(index+". "+player.toString()+System.lineSeparator());
            index++;
        }

        View.Display(OpenMessage);
        if(deterministicMode){
            PlayerSelection = Integer.parseInt(actionReader.nextAction());
        }
        else{
            PlayerSelection = Controller.choosePlayer();
        }
        View.Display("You selected: "+System.lineSeparator()+OptionsforPick.get(PlayerSelection-1).toString());
        View.Display("Use w/s/a/d to move."+System.lineSeparator()+"Use e for special ability or q to pass.");
        return OptionsforPick.get(PlayerSelection-1);
    }

    public void StartGameFlow (){
        update(board.BoardToDisplay());
        while (Hero.getCurrHP() > 0){
            gameTick();
        }
    }
}

