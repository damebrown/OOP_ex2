import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game.
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 * a base class for the other spaceships or any other option you will choose.
 *
 * @author oop
 */
public abstract class SpaceShip {
    
    /**the class constants:
    
    /**
     * a ship's initial maximal energy level.
     */
    protected static final int INITIAL_MAXIMAL_ENERGY_LEVEL = 210;
    
    /**
     * a ship's initial energy level.
     */
    protected static final int INITIAL_ENERGY_LEVEL = 190;
    
    /**
     * a ship's initial health level
     */
    protected static final int INITIAL_HEALTH_LEVEL = 22;
    
    /**
     * the change in energy when bashing occurs
     */
    protected static final int DELTA_BASH = 18;
    
    /**
     * the change in energy when the ship is shot or getting hit
     */
    protected static final int DELTA_HIT_ENERGY = -10;
    
    /**
     * the change in energy when a shot is being fired
     */
    protected static final int DELTA_FIRE = -19;
    
    /**
     * the change in energy when teleportation occurs
     */
    protected static final int DELTA_TELEPORT = -140;
    
    /**
     * the change in energy when the shield is on for a single round
     */
    protected static final int DELTA_SHIELDS = -3;
    
    /**
     * the change in energy for each round
     */
    protected static final int DELTA_ROUND = 1;
    
    /**
     * the change in health when a ship is shot or hit while the shields are down
     */
    protected static final int DELTA_HIT_HEALTH = -1;
    
    /**
     * a boolean parameter, indicating if the shields are on (true) or off (false)
     */
    protected boolean isShieldOn = false;
    
    /**
     * the current maximal energy level of the ship
     */
    protected int maximalEnergyLevel;
    
    /**
     * the current health level of the ship
     */
    protected int healthLevel;
    
    /**
     * the current energy level of the ship
     */
    protected int energyLevel;
    
    /**
     * the ship's SpaceShipPhysics instance
     */
    protected SpaceShipPhysics spaceShipPhysics;
    
    /**
     * a counter used to count the number of rounds passed since the last time a shot was made
     */
    protected int roundsCounter = 1;
    
    /**a boolean flag that changes if a shot was fired*/
    protected boolean wasFired = false;
    
    
    /**
     * the constructor- build a new ship
     */
    public SpaceShip() {
        //initializing maximal energy level, current energy leve, the health level and a new SpaceShipPhysics instance:
        maximalEnergyLevel = INITIAL_MAXIMAL_ENERGY_LEVEL;
        energyLevel = INITIAL_ENERGY_LEVEL;
        healthLevel = INITIAL_HEALTH_LEVEL;
        spaceShipPhysics = new SpaceShipPhysics();
    }
    
    /**
     * a getter function for the inital enery level
     *
     * @return the inital enery level
     */
    public int getInitialEnergyLevel() {
        return INITIAL_ENERGY_LEVEL;
    }
    
    /**
     * a getter function for the initial maximal energy level
     *
     * @return the initial maximal energy level
     */
    public int getInitialMaximalEnergyLevel() {
        return INITIAL_MAXIMAL_ENERGY_LEVEL;
    }
    
    /**
     * a getter function for the initial health level
     *
     * @return the initial health level
     */
    public int getInitialHealthLevel() {
        return INITIAL_HEALTH_LEVEL;
    }
    
    /**
     * getter function for the energy level
     *
     * @return ship's energy level
     */
    public int getEnergyLevel() {
        return energyLevel;
    }
    
    /**
     * getter function for the maximal energy level
     *
     * @return ship's maximal energy level
     */
    public int getMaximalEnergyLevel() {
        return maximalEnergyLevel;
    }
    
    /**
     * getter function for the health level
     *
     * @return ship's maximal energy level
     */
    public int getHealthLevel() {
        return healthLevel;
    }
    
    /**
     * changes the energy level according to the input
     *
     * @param energyChange the wanted change in the energy level. negative number for a reduction, positive number
     *                     for increasing the health level
     */
    public void setEnergyLevel(int energyChange) {
        energyLevel += energyChange;
    }
    
    /**
     * changes the maximal energy level according to the input
     *
     * @param maximalEnergyLevelChange the wanted change in the maximal energy level. negative number for a reduction,
     *                                 positive number for increasing the maximal energy level.
     */
    public void setMaximalEnergyLevel(int maximalEnergyLevelChange) {
        maximalEnergyLevel += maximalEnergyLevelChange;
    }
    
    /**
     * changes the health level according to the input
     *
     * @param healthChange the wanted change in the health level. negative number for a reduction, positive number
     *                     for increasing the health level
     */
    public void setHealthLevel(int healthChange) {
        healthLevel += healthChange;
    }
    
    
    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
    }
    
    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip() {
        //checks if the shield is on and updates the energy level accordingly-
        if (isShieldOn) {
            setEnergyLevel(DELTA_BASH);
            setMaximalEnergyLevel(DELTA_BASH);
        } else {
            //if the shield is off- updating the health, maximal energy level accordingly.
            setHealthLevel(DELTA_HIT_HEALTH);
            setMaximalEnergyLevel(DELTA_HIT_ENERGY);
            if (getMaximalEnergyLevel() < getEnergyLevel()) {
                //if the new maximal energy level is lower then the current energy level, it will be updated
                setEnergyLevel(getMaximalEnergyLevel() - getEnergyLevel());
            }
        }
    }
    
    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        //initializing a new SpaceShipPhysics instance
        spaceShipPhysics = new SpaceShipPhysics();
        //initializing the maximal energy level to the default
        maximalEnergyLevel = getInitialMaximalEnergyLevel();
        //initializing the energy level to the default
        energyLevel = getInitialEnergyLevel();
        //initializing the health level to the default
        healthLevel = getInitialHealthLevel();
        //reset the firing cool down
        roundsCounter = 1;
        
    }
    
    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (getHealthLevel() == 0) {
            return true;
        }
        return false;
    }
    
    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return spaceShipPhysics;
    }
    
    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        //if shield is off, the health and maximal energy will be updated properly. if it is on, no harm was done.
        if (!isShieldOn) {
            setHealthLevel(DELTA_HIT_HEALTH);
            if (getMaximalEnergyLevel()+DELTA_HIT_ENERGY>=0){
                setMaximalEnergyLevel(DELTA_HIT_ENERGY);
            }
            if (getMaximalEnergyLevel() < getEnergyLevel()) {
                setEnergyLevel(getMaximalEnergyLevel() - getEnergyLevel());
            }
        }
    }
    
    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage() {
        if (isShieldOn){
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        } return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }
    
    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        //checking that enough rounds have passed or a fire wasn't shot
        if ((roundsCounter > 7) || (!wasFired)) {
            //checking there's enough energy-
            if (getEnergyLevel()>=-DELTA_FIRE){
            game.addShot(getPhysics());
            setEnergyLevel(DELTA_FIRE);
            roundsCounter = 1;
            wasFired = true;
            }
        }
    }
    
    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        //checks if there is enough energy in order to turn on the shield- and turns it on if there is.
        if (getEnergyLevel() >= -DELTA_SHIELDS) {
            //turn on shield-
            isShieldOn = true;
            //update energy level-
            setEnergyLevel(DELTA_SHIELDS);
        }
    }
    
    /**
     * Attempts to teleport.-
     */
    public void teleport() {
        //checks if there is enough energy in order to teleport- and teleports if there is.
        if (getEnergyLevel() >= -DELTA_TELEPORT) {
            //if so, creating a new SpaceShipPhysics instance
            spaceShipPhysics = new SpaceShipPhysics();
            //updating the energy level accordingly
            setEnergyLevel(DELTA_TELEPORT);
        }
    }
    
}
