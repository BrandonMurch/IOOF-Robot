import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserInputHandlerIT {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  void setup() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void commandLineInput() {

  }

  @Test
  void textFileInputSanityTest() {
    String[] arguments = {"src/test/resources/testCommands.txt"};
    UserInputHandler.main(arguments);
    assertTrue(outputStreamCaptor.toString().length() > 0);
    assertFalse(outputStreamCaptor.toString().contains("File not found."));
  }

  @Test
  void textFileInputTestAllCommandsWithPossibleCollision() {
    String[] arguments = {"src/test/resources/testCommands.txt"};
    UserInputHandler.main(arguments);
    assertTrue(outputStreamCaptor.toString()
        .contains(
            "2 robots are present."));
    assertTrue(outputStreamCaptor.toString()
        .contains(
            "Robot 1: 3,1,EAST"));
    assertTrue(outputStreamCaptor.toString()
        .contains(
            "Robot 2: 3,2,SOUTH"));
    assertTrue(outputStreamCaptor.toString()
        .contains(
            "Active Robot: 2"));
  }

  @Test
  void textFileInputTestPlaceRobotOutsideBoard() {
    String[] arguments = {"src/test/resources/testCommandsRobotOffBoard.txt"};
    UserInputHandler.main(arguments);

    assertTrue(outputStreamCaptor.toString()
        .contains(
            "0 robots are present."));
  }
}