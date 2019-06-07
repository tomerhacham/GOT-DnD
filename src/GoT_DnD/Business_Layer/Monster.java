package GoT_DnD.Business_Layer;

import java.awt.*;

public class Monster extends Enemy{
    private Integer vr;             //Vision range

    public Monster(String name, Integer hp, Integer ap, Integer dp, Point position, Integer xp, char tile, Integer vr,GameUnit Hero){
        super(name, hp, ap, dp, position, xp, tile,Hero);
        this.vr = vr;
    }

    public boolean gameTick() {
        boolean isMoved=false;
        if (rangeToHero() < vr) {
            int dx = this.getPosition().x - super.getHero().getPosition().x;
            int dy = this.getPosition().y - super.getHero().getPosition().y;
            if (Math.abs(dx) > Math.abs(dy)) {
                isMoved = chaseDown(dx, UP, DOWN);
            } else {
                isMoved = chaseDown(dy, LEFT, RIGHT);
            }
        } else {
            int randomMove = GameSystem.randomGenerator.nextInt(5);
            if(randomMove>0 && randomMove<5){
            int legalSituation=Board.isLegalMove(this,randomMove);
            if(legalSituation==0){
                this.Move(randomMove);
                isMoved=true;
            }
            else if(legalSituation==2){
                GameUnit gameUnitAtDes = Board.getGameUnitByPosition(Board.getPointByDirection(this.getPosition(),randomMove));
                if(gameUnitAtDes.stepOn(this)){
                    this.Move(randomMove);
                    isMoved=true;
                }
                else{
                    isMoved=false;
                }
            }
            }
        }
        return isMoved;
    }

    //Checks if Player close enough to chase, and where to move
    private boolean chaseDown(int axis, int neg, int pos) {
        boolean isMoved=false;
        if (axis > 0){
            int legalSituation=Board.isLegalMove(this, neg);
            if (legalSituation==0) {
                this.Move(neg);
                isMoved=true;
            }
            else if(legalSituation==2){
                GameUnit gameUnitAtDes = Board.getGameUnitByPosition(Board.getPointByDirection(this.getPosition(),neg));
                if(gameUnitAtDes.stepOn(this)){
                    this.Move(neg);
                    isMoved=true;
                }
                else{
                    isMoved=false;
                }

            }
        }
        else{
            int legalSituation=Board.isLegalMove(this, pos);
            if (legalSituation==0) {
                this.Move(pos);
                isMoved=true;
            }
            else if(legalSituation==2){
                GameUnit gameUnitAtDes = Board.getGameUnitByPosition(Board.getPointByDirection(this.getPosition(),pos));
                if(gameUnitAtDes.stepOn(this)){
                    this.Move(pos);
                    isMoved=true;
                }
                else{
                    isMoved=false;
                }

            }
        }
        return isMoved;
    }
}
