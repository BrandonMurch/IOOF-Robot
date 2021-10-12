import java.util.Optional;

public class ToyFactory {

  private static void printArgumentHelpMessage() {
    System.out.println("Invalid arguments. Please enter in INTEGER, INTEGER, FACINGOPTION");
  }

  public static Optional<MovableToy> createToyFromStrings(String[] arguments, MoveVerifier verifier) {
    if (arguments.length != 3) {
      printArgumentHelpMessage();
      return Optional.empty();
    }

    if (verifier.canMove(
        new Coordinates(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1])))) {
      Robot robot;
      try {
        robot = new Robot(
            Integer.parseInt(arguments[0]),
            Integer.parseInt(arguments[1]),
            FacingOption.valueOf(arguments[2].toUpperCase().trim()),
            verifier
        );
      } catch (NumberFormatException ignored) {
        printArgumentHelpMessage();
        return Optional.empty();
      }
      return Optional.of(robot);
    }
    return Optional.empty();
  }
}
