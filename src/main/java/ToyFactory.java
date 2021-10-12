import java.util.Optional;

public class ToyFactory {

  private static void printArgumentHelpMessage() {
    System.out.println("Invalid arguments. Please enter in INTEGER, INTEGER, DIRECTION");
  }

  // Will return Optional.empty if there are invalid arguments.
  // Arguments should be formed like: x,y,DIRECTION
  public static Optional<MovableToy> createToyFromStrings(String[] arguments,
      MoveVerifier verifier) {
    if (arguments.length != 3) {
      printArgumentHelpMessage();
      return Optional.empty();
    }

    if (verifier.canMove(
        new Coordinates(
            Integer.parseInt(arguments[0]),
            Integer.parseInt(arguments[1])
        ))) {
      Robot robot;
      try {
        robot = new Robot(
            Integer.parseInt(arguments[0]),
            Integer.parseInt(arguments[1]),
            FacingOption.valueOf(arguments[2].toUpperCase().trim()),
            verifier
        );
      } catch (IllegalArgumentException ignored) {
        printArgumentHelpMessage();
        return Optional.empty();
      }
      return Optional.of(robot);
    }
    return Optional.empty();
  }
}
