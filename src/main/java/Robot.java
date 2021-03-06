public class Robot implements MovableToy {

  private final MoveVerifier controller;
  private final OutputInterface output = new OutputTerminal();
  private Coordinates location;
  private FacingOption facing;

  public Robot(int x, int y, FacingOption facing, MoveVerifier controller) {
    location = new Coordinates(x, y);
    this.facing = facing;
    this.controller = controller;
  }

  @Override
  public String getIdentity() {
    return "Robot";
  }

  @Override
  public void printLocation() {
    output.println(String.format(
        "%d,%d,%s",
        location.x,
        location.y,
        facing
    ));
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


  private Coordinates calculateNewLocation() throws IllegalStateException {
    switch (facing) {
      case NORTH :
        return new Coordinates(location.x, location.y + 1);
      case EAST :
        return new Coordinates(location.x + 1, location.y);
      case SOUTH :
        return new Coordinates(location.x, location.y - 1);
      case WEST : 
        return new Coordinates(location.x - 1, location.y);
    };

    throw new IllegalStateException("Facing direction is not set");
  }

  // Keep integer within options list. Example: List length = 4, 5 becomes 1.
  int keepIndexWithinBounds(int value, int length) {
    int index = value % (length);
    // Since Java keeps negative values negative after a modulus operation, we must add the length
    // to get the positive value.
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
