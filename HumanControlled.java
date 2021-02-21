import oop.ex2.GameGUI;
import java.awt.*;

public class HumanControlled extends SpaceShip {
    
    /**
     * the constructor method
     */
    public HumanControlled(){
    }
    
    /**
     * Does the actions of a human controlled ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        GameGUI thisGUI = game.getGUI();
        //initialize turning flag, which changes if left or right is pressed-
        int turn = 0;
        //first, calling the teleport method if teleport is pressed-
        if (thisGUI.isTeleportPressed()){
            teleport();
        }
        //acceleration and turning managing- changing the turn flag if left was pressed OR right was pressed-
        if (thisGUI.isLeftPressed()){
            turn = 1;
        } if (thisGUI.isRightPressed()){
            if (turn!=1){
                turn = -1;
            } else {
                //if both were pressed, no turn will occur (turn will be equal 0)
                turn = 0;
            }
        }
        //calling the move function with the relevant variables-
        spaceShipPhysics.move(thisGUI.isUpPressed(), turn);
        //shield managing-
        //every turn begins when the shield is off (shield duration is 1 round)
        isShieldOn = false;
        //calling 'shieldOn'  if shield was pressed-
        if (thisGUI.isShieldsPressed()){
            shieldOn();
        }
        //shot managing-
        //counting rounds (if counting is greater then 7, it turns 1 again)
        roundsCounter++;
        //calling the fire function if Shot was pressed
        if (thisGUI.isShotPressed()){
            fire(game);
        }
        //increasing energy by 1
        setEnergyLevel(DELTA_ROUND);
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage(){
        //overrides the getImage function of the SpaceShip class- this is the only case where the image will
        //be the one of the human.
        //sending the shield image if the shield is on, and the non-shield image otherwise
        if (isShieldOn){
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        } return GameGUI.SPACESHIP_IMAGE;
    }

}
