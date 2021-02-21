public class Basher extends SpaceShip{
    
    /**class constants:
    /**the minimal distance from which the basher ship will turn on it's shields*/
    private static final double MAXIMAL_DISTANCE = 0.19;
    
    /**
     * the constructor method- implicitly calls the SpaceShip constructor
     */
    public Basher(){
    }
    
    /**
     * Does the actions of a basher ship for this round.
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
        //if the distance from the closest ship is smaller then MAXIMAL_DISTANCE, calling 'shieldOn'-
        if (spaceShipPhysics.distanceFrom(closestShip.getPhysics())<=MAXIMAL_DISTANCE){
            shieldOn();
        }
        //shot managing-
        //counting rounds (if counting is greater then 7, it turns 1 again)
        roundsCounter++;
        //calling the fire function
        fire(game);
        //increasing energy by 1
        setEnergyLevel(DELTA_ROUND);
    }
    
}
