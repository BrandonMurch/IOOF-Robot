import java.util.Scanner;

public abstract class InputAbstract {

  Scanner input;

  public UserInput(Scanner input) {
    this.input = input;
  }

  public boolean hasNext() {
    return input.hasNext();
  }

  public String nextLine() {
    return input.nextLine();
  }

  public void close() {
    input.close();
  }

}
