import java.io.FileNotFoundException;

public class UserInputHandler {

  public static void handleInput(UserInput inputScanner, Board board) {
    InputOption selectedOption = null;

    while (inputScanner.hasNext() && selectedOption != InputOption.QUIT) {
      String[] userInputSplit = inputScanner
          .nextLine()
          .toUpperCase()
          .split(" ");

      try {
        selectedOption = InputOption.valueOf(userInputSplit[0]);
      } catch (Exception ignored) {
        System.out.println("Invalid Input");
        continue;
      }

      board.applyOption(selectedOption, userInputSplit.length > 1
          ? userInputSplit[1]
          : "");

    }
  }


  public static void main(String[] argv) {
    UserInput inputScanner;
    if (argv.length == 1) {
      try {
        inputScanner = new FileInput(argv[0]);
      } catch (FileNotFoundException e) {
        System.out.println("File not found.");
        return;
      }
    } else {
      inputScanner = new TerminalInput();
    }

    Board board = new Board(5, 5);

    handleInput(inputScanner, board);
    inputScanner.close();
  }
}


