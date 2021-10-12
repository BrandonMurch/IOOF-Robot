import java.util.Scanner;

public abstract class UserInput {

  Scanner input;

  public UserInput(Scanner input) {
    this.input = input;
  }

  abstract public boolean hasNext();

  public String nextLine() {
    return input.nextLine();
  }

  public Scanner getUnderlyingScanner() {
    return input;
  }

  public void close() {
    input.close();
  }

}
