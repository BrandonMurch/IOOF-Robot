import java.util.Optional;

public class ToyFactory {

  public static Optional<MovableToy> createToy(String[] arguments, MoveVerifier verifier) {
    if (arguments.length != 3) {
      return Optional.empty();
    }

    if (verifier.canMove(
        new Coordinates(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1])))) {
      return Optional.of(new Robot(
          Integer.parseInt(arguments[0]),
          Integer.parseInt(arguments[1]),
          FacingOption.valueOf(arguments[2].trim()),
          verifier
      ));
    }
    return Optional.empty();
  }
}
