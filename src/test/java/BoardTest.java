import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  Board board = new Board(5, 5);

  @BeforeEach
  void setup() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  void tearDown() {
    System.setOut(System.out);
  }

  @Test
  void cantMoveOutsideBoard() {
    assertFalse(board.canMove(new Coordinates(-1, 3)));
    assertFalse(board.canMove(new Coordinates(-1, -3)));
    assertFalse(board.canMove(new Coordinates(0, 6)));
    assertFalse(board.canMove(new Coordinates(5, 0)));
  }

  @Test
  void canMoveInsideBoard() {
    assertTrue(board.canMove(new Coordinates(3, 0)));
    assertTrue(board.canMove(new Coordinates(4, 0)));
  }

  @Test
  void cantMoveToOccupiedSpace() {
    Robot robot = new Robot(3, 3, FacingOption.NORTH, board);
    board.addToy(robot);
    assertFalse(board.canMove(new Coordinates(3, 3)));
  }

  @Test
  void place() {
    int robotCountBefore = board.getToys().size();
    board.applyOption(InputCommandOption.PLACE, "2,2,NORTH");
    assertEquals(robotCountBefore + 1, board.getToys().size());
    board.applyOption(InputCommandOption.PLACE, "2,-1,NORTH");
    assertEquals(robotCountBefore + 1, board.getToys().size());
    board.applyOption(InputCommandOption.PLACE, "5,2,NORTH");
    assertEquals(robotCountBefore + 1, board.getToys().size());
  }

  @Test
  void reportOneRobot() {
    Robot robot = new Robot(3, 3, FacingOption.NORTH, board);
    board.addToy(robot);
    board.applyOption(InputCommandOption.REPORT, "");
    assertTrue(outputStreamCaptor.toString().contains("1 robot is present."));
    assertTrue(outputStreamCaptor.toString().contains("Active Robot: 1"));
  }

  @Test
  void reportTwoRobots() {
    board.addToy(new Robot(3, 3, FacingOption.NORTH, board));
    board.addToy(new Robot(4, 4, FacingOption.NORTH, board));

    board.applyOption(InputCommandOption.REPORT, "");
    assertTrue(outputStreamCaptor.toString().contains("2 robots are present."));
    assertTrue(outputStreamCaptor.toString().contains("Active Robot: 1"));
  }

  @Test
  void changeActive() {
    board.addToy(new Robot(3, 3, FacingOption.NORTH, board));
    board.addToy(new Robot(4, 4, FacingOption.NORTH, board));
    board.applyOption(InputCommandOption.ROBOT, "2");

    board.applyOption(InputCommandOption.REPORT, "");
    assertTrue(outputStreamCaptor.toString().contains("2 robots are present."));
    assertTrue(outputStreamCaptor.toString().contains("Active Robot: 2"));
  }
}