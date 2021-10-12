# IOOF Robot Challenge

### Executing program from command line

To execute this program, run using the entry class UserInputHandler. For example: \
```java -classpath target/classes UserInputHandler ```

This program has two options for commandline arguments

1. No argument (This will cause the program to rely on further command line instructions)
2. File name / path based on present working directory (This will read in commands from the
   specified text file). Please put each command on a new line.

### Possible options within the program:

1. ```PLACE X,Y,DIRECTION``` - Place a robot at the supplied x,y position, and face it in the <br />
   supplied direction. The possible direction options are ```NORTH```, ```EAST```, ```SOUTH```
   , ```WEST```.
2. ```MOVE``` - Move the robot one place ahead in the direction it is currently facing.
3. ```LEFT``` - Turn the robot 90 degrees to the left. Position remains unchanged.
4. ```RIGHT``` - Turn the robot 90 degrees to the right. Position remains unchanged.
5. ```ROBOT NUMBER``` - Changes the current active robot to the specified number starting at 1.
6. ```REPORT``` - Reports the number of robots on the board, each robots position, and the active
   robot.

### Further information

Visit https://github.com/ioof-holdings/recruitment/wiki/Robot-Challenge for more information.