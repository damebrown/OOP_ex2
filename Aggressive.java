public class Aggressive extends SpaceShip {
    
    /**class constants:
    /**the minimal angle from which the aggressive ship will attempt to shoot*/
    private static final double MINIMAL_NEUTRAL_ANGLE = 0.21;
    
    /**
     * the constructor method- implicitly calls the SpaceShip constructor
     */
    public Aggressive(){
    }
    
    /**
     * Does the actions of an aggressive ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        //initializing turn indicator-
        int turn=0;
        //calling the getClosestShipTo, in order to define whether where to turn to
        SpaceShip closestShip = game.getClosestShipTo(this);
        //checking the angle to the closest ship-
        double angle = spaceShipPhysics.angleTo(closestShip.spaceShipPhysics);
        //if angle is negative- turning towards the closest ship will be turning right
        if (angle<0){
            turn = -1;
        } else {
            //if angle is positive- turning towards the closest ship will be turning left
            turn = 1;
            //now calling the move function with the acceleration always on and the suitable turning direction-
        } spaceShipPhysics.move(true, turn);
        //shield managing-
        //every turn begins when the shield is off (shield duration is 1 round)
        isShieldOn = false;
        shieldOn();
        //shot managing-
        //counting rounds (if counting is greater then 7, it turns 1 again)
        roundsCounter++;
        //if the angle from the closest ship is smaller then MINIMAL_NEUTRAL_ANGLE, calling 'fire'-
        if (angle<MINIMAL_NEUTRAL_ANGLE){
        //calling the fire function
            fire(game);
        }
        //increasing energy by 1
        setEnergyLevel(DELTA_ROUND);
    }
}
