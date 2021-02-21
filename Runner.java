import oop.ex2.GameGUI;

import java.awt.*;

public class Runner extends SpaceShip{
    
    /**class constants:
    /**the minimal angle in which the runner ship will not attempt to teleport*/
    private static final double CLOSEST_ANGLE = 0.23;
    
    /**the minimal position in which the runner ship will not attempt to teleport*/
    private static final double CLOSEST_POSITION = 0.23;
    
    /**
     * the constructor method- implicitly calls the SpaceShip constructor
     */
    public Runner(){
    }
    
    /**
     * Does the actions of a runner ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        //initializing turn indicator-
        int turn=0;
        //calling the getClosestShipTo, in order to define whether to teleport or not
        SpaceShip closestShip = game.getClosestShipTo(this);
        //checking the angle to the closest ship-
        double angle = spaceShipPhysics.angleTo(closestShip.spaceShipPhysics);
        //checking if angle and position are suitable to try and teleport-
        if ((angle<CLOSEST_ANGLE)&&(spaceShipPhysics.distanceFrom(closestShip.getPhysics())<CLOSEST_POSITION)){
            teleport();
        } else {
            //otherwise- calling the move function according the angle of the closest ship
            //if angle is negative- turning away would be turning left
            if (angle<0){
                turn = 1;
            } else {
                //if angle is positive- turning away would be turning right
                turn = -1;
                //now calling the move function with the acceleration always on and the suitable turning direction-
            }
        } spaceShipPhysics.move(true, turn);
        //shield managing-
        //every turn begins when the shield is off (shield duration is 1 round)
        isShieldOn = false;
        //calling 'shieldOn'-
        shieldOn();
        //shot managing-
        //counting rounds (if counting is greater then 7, it turns 1 again)
        roundsCounter++;
        //calling the fire function
        fire(game);
        //increasing energy by 1
        setEnergyLevel(DELTA_ROUND);
        }
        
}

