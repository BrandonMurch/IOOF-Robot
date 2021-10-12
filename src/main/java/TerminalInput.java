import java.util.Scanner;

public class TerminalInput extends UserInput {

  public TerminalInput() {
    super(new Scanner(System.in));
  }

  @Override
  public boolean hasNext() {
    return true;
  }
}

