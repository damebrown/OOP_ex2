import java.util.Random;

public class Drunkard extends SpaceShip {
    
    /**class constants:
    /**round counter- in order to create a fluent behaviour (allowing the ship to turn sometimes), we will count the
     * turns and change the behaviour every 10th turn-**/
     private int behaviorRoundCounter = 0;
     
     /**in order to maintain the same behaviour, we will save the chosen turning and accelerating policies*/
     private int currentTurningPolicy = 0;
     private boolean currentAcceleratingPolicy = true;
    
    /**
     * the constructor method- implicitly calls the SpaceShip constructor
     */
    public Drunkard(){
    }
    
    /**
     * Does the actions of a drunkard ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * the drunkand ship is randomizing the behaviour for the move function every 10th round.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        //counting the round-
        behaviorRoundCounter++;
        //randomizing a number
        Random myRandom = new Random();
        int randomBehaviour = myRandom.nextInt(3);
        //if it's the 10th round we will generate a new random behaviour
        if (behaviorRoundCounter ==10){
            //randomizing a number between 0 and 3 (exclusive), when every number has a unique behavior-
            // if the number divides by 2 (0 and 2), the ship will accelerate, and will not, otherwise.
            //the turning will be the number -1, this way every option for turning (-1,0,1) will be optional.
            if (randomBehaviour % 2 == 0){
                currentAcceleratingPolicy = true;
            } currentTurningPolicy = randomBehaviour - 1;
            spaceShipPhysics.move(currentAcceleratingPolicy, currentTurningPolicy);
            //zeroing the behaviourRoundCounter:
            behaviorRoundCounter = 0;
            //else- we will take the same behaviour
        } else {
            spaceShipPhysics.move(currentAcceleratingPolicy, currentTurningPolicy);
        }
        //shield managing-
        //every turn begins when the shield is off (shield duration is 1 round)
        // the shield will be activated only of the random number is 1
        isShieldOn = false;
        if (randomBehaviour == 1){
            shieldOn();
        }
        //shot managing-
        //counting rounds (if counting is greater then 7, it turns 1 again)
        roundsCounter++;
        //the Drunkard ship will attempt to shoot only if the random number is 0
        if (randomBehaviour == 0){
            //calling the fire function
            fire(game);
        }
        //increasing energy by 1
        setEnergyLevel(DELTA_ROUND);
    }
    
}
