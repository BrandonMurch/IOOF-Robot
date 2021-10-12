public class Coordinates {

  public int x;
  public int y;

  public Coordinates(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean equals(Coordinates coordinates) {
    return x == coordinates.x && y == coordinates.y;
  }
}
