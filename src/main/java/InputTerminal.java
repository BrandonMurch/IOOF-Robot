import java.util.Scanner;

public class InputTerminal extends InputAbstract {

  public InputTerminal() {
    super(new Scanner(System.in));
  }

  @Override
  public boolean hasNext() {
    return true;
  }
}

