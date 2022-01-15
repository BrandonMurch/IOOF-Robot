# IOOF Robot Challenge

## Approach to solving the problem
I took an object-oriented to minimize global mutable state which improves stability of the data and to provide easy extensibility in the future. 

Each robot controls its own position. When a move is requested, the robot verifies with the board that the move is possible, then it will update its own private variable. To verify the move, the board first checks the requested location against the board borders. Then checks with each robot on the board to see if there is already a robot in the requested location. 

Using this approach, the time complexity of moving a robot is increased from O(1) to O(n) where n is the number of robots. However the time complexity for reporting all robots and changing between robots is reduced compared to a naive approach of using a matrix from O(x * y) where x and y are the dimensions of the board, to O(n). This provides a great advantage for a sparsely filled board, but less so for a densely filled one.

The program allows for future expansion of the program with the ability to add more toys that move differently by extending the movableToy class. For example, a "knight" piece could be easily added in the future which moves forward twice and left once.

The method of input can be changed depending on the command line arguments. See the following section for how this is done. This can be expanded further in the future by creating a new class which extends InputAbstract.

<br><br>

## Executing program from command line

Within the main directory of this program are the .jar file and a commands.txt file for testing. To execute this, ensure both the .jar file and the commands.txt file are within the same directory. Then run:

```java -jar IOOF.Robot-1.0.jar commands.txt ```

More generally, this program has two options for command line arguments

1. No argument (This will cause the program to rely on further command line instructions)
2. File name / path based on present working directory (This will read in commands from the specified text file). Please put each command on a new line.

<br><br>

## Environment Specifics
This program is written in Java 13, uses Maven to handle dependencies and compiling, and was compiled on Linux using openjdk-13.

To re-compile the jar, run  ```mvn package```. 

To run all unit / integration tests, run  ```mvn test```

<br><br>


## Possible options within the program:

1. ```PLACE X,Y,DIRECTION``` - Place a robot at the supplied x,y position, and face it in the <br />
   supplied direction. The possible direction options are ```NORTH```, ```EAST```, ```SOUTH```
   , ```WEST```.
2. ```MOVE``` - Move the robot one place ahead in the direction it is currently facing.
3. ```LEFT``` - Turn the robot 90 degrees to the left. Position remains unchanged.
4. ```RIGHT``` - Turn the robot 90 degrees to the right. Position remains unchanged.
5. ```ROBOT NUMBER``` - Changes the current active robot to the specified number starting at 1.
6. ```REPORT``` - Reports the number of robots on the board, each robots position, and the active
   robot.

### Further information about the challenge

Visit https://github.com/ioof-holdings/recruitment/wiki/Robot-Challenge for more information.