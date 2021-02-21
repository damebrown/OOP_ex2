import java.util.Random;

public class Special extends SpaceShip {
    
    
    /**class constants:
    /**the minimal distance from which the special ship will turn on it's shields*/
    private static final double MAXIMAL_DISTANCE = 0.19;
    
    /**each instance will have it's own unique randomized turning behaviour*/
    private int randomTurningBehaviour;
    
    /**
     * the constructor method- implicitly calls the SpaceShip constructor
     */
    public Special(){
        Random myRandom = new Random();
        int randomNumber = myRandom.nextInt(3);
        randomTurningBehaviour = randomNumber - 1;
    }
    
    /**
     * Does the actions of a special ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * The behaviour of the special spaceship is- never accelerating, teleporting if possible, and shooting if not.
     * when the special ship is close to a different ship, it will attempt to activate the shield. in addition, the
     * firing of the special ship is not dependent on the rounds (there's no cool-down waiting), and it can fire any
     * time it desires (in condition that it has enough energy).
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        spaceShipPhysics.move(false, randomTurningBehaviour);
        //counting rounds (if counting is greater then 7, it turns 1 again)
        roundsCounter++;
        if (getEnergyLevel()>-DELTA_TELEPORT){
            teleport();
        } else {
            //calling the fire function
            fire(game);
        }
        //shield managing-
        //every turn begins when the shield is off (shield duration is 1 round)
        isShieldOn = false;
        // the shield will be activated when there is a close enough ship-
        SpaceShip closestShip = game.getClosestShipTo(this);
        if (spaceShipPhysics.distanceFrom(closestShip.getPhysics())<=MAXIMAL_DISTANCE){
            shieldOn();
        }
        //increasing energy by 1
        setEnergyLevel(DELTA_ROUND);
    }
    
    /**
     * this function overrides the fire function of the space ship class. it allows the special ship
     * to fire at all times, with no restrictions
     * @param game the game object.
     */
    public void fire(SpaceWars game){
        if (getEnergyLevel()>-DELTA_FIRE){
            game.addShot(getPhysics());
            setEnergyLevel(DELTA_FIRE);
        }
    }
}
