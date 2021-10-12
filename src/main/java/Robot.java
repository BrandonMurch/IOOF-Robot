public class Robot implements MovableToy {

  private Coordinates location;
  private FacingOption facing;
  private final MoveVerifier controller;

  public Robot(int x, int y, FacingOption facing, MoveVerifier controller) {
    location = new Coordinates(x, y);
    this.facing = facing;
    this.controller = controller;
  }

  @Override
  public void printLocation() {
    printLocation("");
  }

  @Override
  public void printLocation(String index) {
    System.out.printf(
        "Robot %s: %d,%d,%s \n",
        index,
        location.x,
        location.y,
        facing
    );
  }

  @Override
  public Coordinates getLocation() {
    return location;
  }

  @Override
  public void move() {
    Coordinates new_location = calculateNewLocation();
    if (controller.canMove(new_location)) {
      this.location = new_location;
    }
  }

  @Override
  public void turnLeft() {
    turn(this.facing.ordinal() - 1);

  }

  @Override
  public void turnRight() {
    turn(this.facing.ordinal() + 1);
  }


  private Coordinates calculateNewLocation() {
    return switch (facing) {
      case NORTH -> new Coordinates(location.x, location.y + 1);
      case EAST -> new Coordinates(location.x + 1, location.y);
      case SOUTH -> new Coordinates(location.x, location.y - 1);
      case WEST -> new Coordinates(location.x - 1, location.y);
    };
  }

  // Keep integer within options list. Example: List length = 4, 5 becomes 1.
  int keepIndexWithinBounds(int value, int length) {
    int index = value % (length);
    if (index < 0) {
      index += length;
    }
    return index;
  }

  private void turn(int facingIndex) {

    facing = FacingOption.values()[
        keepIndexWithinBounds(facingIndex, FacingOption.values().length)
        ];
  }
}
