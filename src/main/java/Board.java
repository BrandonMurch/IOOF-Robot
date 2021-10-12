import java.util.ArrayList;

public class Board implements MoveVerifier {

  private final ArrayList<MovableToy> toys;
  private MovableToy activeToy = null;
  private final int width;
  private final int height;

  protected ArrayList<MovableToy> getToys() {
    return toys;
  }

  public Board(int width, int height) {
    toys = new ArrayList<>();
    this.width = width;
    this.height = height;
  }

  protected void addToy(MovableToy toy) {
    toys.add(toy);
    if (activeToy == null) {
      activeToy = toy;
    }
  }

  private void changeActiveToy(int index) {
    activeToy = toys.get(index);
    activeToy.printLocation();
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

  @Override
  public boolean canMove(Coordinates coordinates) {
    if (isOffBoard(coordinates)) {
      return false;
    }
    return isSpaceFree(coordinates);
  }

  private void reportToys() {
    System.out.printf("%d robot%s present.\n", toys.size(), toys.size() != 1 ? "s are" : " is");
    int active = -1;

    for (int i = 0; i < toys.size(); i++) {
      MovableToy toy = toys.get(i);
      System.out.printf("%d: ", i);
      toy.printLocation();
      if (toy == activeToy) {
        active = i + 1;
      }
    }

    if (active != -1) {
      System.out.printf("Active: %d\n", active);
    }
  }


  public void applyOption(InputOption inputOption, String argumentString) {
    switch (inputOption) {
      case ROBOT -> changeActiveToy(
          Integer.parseInt(argumentString) - 1
      );
      case MOVE -> activeToy.move();
      case LEFT -> activeToy.turnLeft();
      case RIGHT -> activeToy.turnRight();
      case REPORT -> reportToys();
      case PLACE -> ToyFactory
          .createToy(argumentString.split(","), this)
          .ifPresent(this::addToy);
    }
  }
}
