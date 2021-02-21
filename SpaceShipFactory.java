public class SpaceShipFactory {
    
    /**
     * receives an array of ship types. iterates over the array and calls the constructor method of each wanted
     * ship-type.
     * @param args an array of the one letter strings, each representing a wanted ship in the game
     * @return an array of SpaceShip instances which will play in the game
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        //initializing the SpaceShips array-
        SpaceShip[] shipsArray = new SpaceShip[args.length];
        for (int argsIndex=0;argsIndex<args.length;argsIndex++){
            if (args[argsIndex].equals("h")){
                shipsArray[argsIndex] = new HumanControlled();
            }
            if (args[argsIndex].equals("r")){
                shipsArray[argsIndex] = new Runner();
            }
            if (args[argsIndex].equals("b")){
                shipsArray[argsIndex] = new Basher();
            }
            if (args[argsIndex].equals("a")){
                shipsArray[argsIndex] = new Aggressive();
            }
            if (args[argsIndex].equals("d")){
                shipsArray[argsIndex] = new Drunkard();
            }
            if (args[argsIndex].equals("s")){
                shipsArray[argsIndex] = new Special();
            }
        }
        return shipsArray;
    }
}
