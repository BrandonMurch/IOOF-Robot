import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class RobotTest {

  class MockVerifier implements MoveVerifier {

    @Override
    public boolean canMove(Coordinates coordinates) {
      return true;
    }
  }


  MockVerifier verifier = new MockVerifier();
  Robot robot = new Robot(1, 1, FacingOption.NORTH, verifier);


  @Test
  void SanityCheck() {
    assertEquals(1, robot.getLocation().x);
    assertEquals(1, robot.getLocation().y);
  }

  @Test
  void move() {
    robot.move();
    assertEquals(2, robot.getLocation().y);
  }

  @Test
  void turnLeftAndMove() {
    robot.turnLeft();
    robot.move();
    assertEquals(0, robot.getLocation().x);
  }

  @Test
  void turnRightAndMove() {
    robot.turnRight();
    robot.move();
    assertEquals(2, robot.getLocation().x);
  }

}