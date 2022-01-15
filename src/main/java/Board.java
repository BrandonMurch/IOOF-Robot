import java.util.ArrayList;

public class Board implements MoveVerifier {

  private final ArrayList<MovableToy> toys;
  private final int width;
  private final int height;
  private final OutputInterface output = new OutputTerminal();
  private MovableToy activeToy = null;

  public Board(int width, int height) {
    toys = new ArrayList<>();
    this.width = width;
    this.height = height;
  }

  @Override
  public boolean canMove(Coordinates coordinates) {
    if (isOffBoard(coordinates)) {
      return false;
    }
    return isSpaceFree(coordinates);
  }

  // All InputOptions are handled, except QUIT.
  public void applyOption(InputCommandOption inputOption, String argumentString) {
    switch (inputOption) {
      case ROBOT -> changeActiveToy(
          Integer.parseInt(argumentString) - 1
      );
      case MOVE -> activeToy.move();
      case LEFT -> activeToy.turnLeft();
      case RIGHT -> activeToy.turnRight();
      case REPORT -> reportToys();
      case PLACE -> ToyFactory
          .createToyFromStrings(argumentString.split(","), this)
          .ifPresent(this::addToy);
    }
  }

  protected ArrayList<MovableToy> getToys() {
    return toys;
  }

  protected void addToy(MovableToy toy) {
    toys.add(toy);
    if (activeToy == null) {
      activeToy = toy;
    }
  }

  private void changeActiveToy(int index) {
    if (index < 0 || index >= toys.size()) {
      output.println("Invalid robot index selected.");
    } else {
      activeToy = toys.get(index);
      output.print("Active location: ");
      activeToy.printLocation();
    }
  }

  private boolean isOffBoard(Coordinates coordinates) {
    return coordinates.x < 0 || coordinates.x >= width || coordinates.y < 0
        || coordinates.y >= height;
  }

  private boolean isSpaceFree(Coordinates coordinates) {
    for (MovableToy toy : toys) {
      Coordinates currentlyChecking = toy.getLocation();
      if (coordinates.equals(currentlyChecking)) {
        return false;
      }
    }
    return true;
  }

  private void reportToys() {
    output.println(
        String.format("%d robot%s present.", toys.size(), toys.size() != 1 ? "s are" : " is"));
    int active = -1;

    for (int i = 0; i < toys.size(); i++) {
      MovableToy toy = toys.get(i);
      output.print(String.format("%s %d: ", toy.getIdentity(), i + 1));
      toy.printLocation();
      if (toy == activeToy) {
        active = i + 1;
      }
    }

    if (active != -1) {
      output.println(String.format("Active %s: %d", toys.get(active - 1).getIdentity(), active));
    } else {
      output.println("No active toy");
    }
  }


}
