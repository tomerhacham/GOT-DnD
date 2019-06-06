package GoT.DnD.Business_Layer;
import GoT.DnD.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class GameSystem implements Observer {
    //Fields
    private Board board;
    private Player Hero;
    private LinkedList<Player> OptionsforPick;
    private boolean deterministicMode = false;
    public static ActionReader actionReader;
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
        OptionsforPick.add(new Warrior ("Jon Snow",300, 30,4,new Point(0,0),6,'@'));
        OptionsforPick.add(new Warrior ("The Hound",400, 20,6,new Point(0,0),4,'@'));
        OptionsforPick.add(new Mage("Melisandre",160,10,1,new Point(0,0),40,300,30,5,6,'@'));
        OptionsforPick.add(new Mage("Thoros of Myr",250,25,3,new Point(0,0),15,150,50,3,3,'@'));
        OptionsforPick.add(new Rogue("Arya Stark",150,40,2,new Point(0,0),20,'@'));
        OptionsforPick.add(new Rogue("Bronn",250,35,3,new Point(0,0),60,'@'));
        //endregion
        this.levelNames=levelNames;
        this.deterministicMode=deterministicMode;
        actionReader=new ActionReader(deterministicMode);
        randomGenerator=new RandomGenerator(deterministicMode);
        this.Hero=initialize(deterministicMode);
        this.Hero.register(this);
        board = new Board(levelNames.get(0),Hero);
        CombatSystem combatSystem = new CombatSystem();
        combatSystem.register(this);
    }
    //region Methods
    public void gameTick(){
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
                if (board.Level < levelNames.size()) {
                    update("Good Job, get ready for the next mission!");
                    this.LoadLevel();
                } else {
                    update("Game is finished. You won!");
                    System.exit(0);
                }
            }
            update(board.BoardToDisplay());
    }

    public void castSpecialAbility(){
        board.castSpecialAbility();
    }

    private void LoadLevel(){
        this.board= new Board("level "+board.Level+1+".txt",Hero);

    }
    //region Observer implement
    @Override
    public void update(Object message) {
        View.Display((String)message);
    }
    //endregion

    public Player initialize(boolean deterministicMode){
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

    public static void main(String[] args) {
    //    args = new String[2];
    //    args[0] = System.getProperty("user.dir")+"\\src\\GoT\\DnD\\Persistent_Layer\\Levels";
    //    args[1]="-D";
        List<String> levels = new LinkedList<>();
        boolean deterministicFlag=false;

        if(args.length!=0){//some args has been pass

            File root = new File(args[0]); //path for level files
            for(File f:root.listFiles()){
                levels.add(f.getAbsolutePath());
            }

            if(args.length>1 && args[1].equals("-D")){//deterministic flag was raised
                deterministicFlag=true;
            }
        }
        else{
            File root = new File(System.getProperty("user.dir")+"\\src\\GoT\\DnD\\Persistent_Layer\\Levels");
            for(File f:root.listFiles()){
                levels.add(f.getAbsolutePath());
            }
        }

        GameSystem g = new GameSystem(deterministicFlag,levels);
        g.StartGameFlow();
    }
}

