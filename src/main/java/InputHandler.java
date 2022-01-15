import java.io.FileNotFoundException;

public class InputHandler {


  private static void printHelp() {
    System.out.println("Possible commands:");
    System.out.print("PLACE X,Y,F \t\t Place a new robot onto the board at the specified X and Y location, facing in the F direction. F can be one of the following: ");
    System.out.println(java.util.Arrays.asList(FacingOption.values()));
    System.out.println(" MOVE \t\t Move the robot 1 space forward.");
    System.out.println(" LEFT \t\t Turn the robot 90 degrees counter-clockwise.");
    System.out.println(" RIGHT \t\t Turn the robot 90 degrees clockwise.");
    System.out.println(" Report \t\t Report all robots on the board, and which one is active.");
    System.out.println(" ROBOT N \t\t Select the N'th robot that was placed on the board.");
  }

  public static void handleInput(InputAbstract inputScanner, Board board) {
    InputCommandOption selectedOption = null;

//    If using command line, hasNext will always be true to allow more commands to be entered.
//    This will rely on quit to exit. Files will return false to hasNext at the end of the file.
    while (inputScanner.hasNext() && selectedOption != InputCommandOption.QUIT) {
      String[] userInputSplit = inputScanner
          .nextLine()
          .toUpperCase()
          .split(" ");

      try {
        selectedOption = InputCommandOption.valueOf(userInputSplit[0]);
      } catch (Exception ignored) {
        System.out.println("Invalid Input");
        continue;
      }

      board.applyOption(selectedOption, userInputSplit.length > 1
          ? userInputSplit[1]
          : "");

    }
  }


  // Entry point. Either provide no arguments, or a single argument in the form of a text file.
  public static void main(String[] argv) {
    InputAbstract inputScanner;
    if (argv.length == 1) {
      if (argv[0].trim().toLowerCase().equals("help")) {
        printHelp();
        return;
      } 
      try {
        inputScanner = new InputFile(argv[0]);
      } catch (FileNotFoundException e) {
        System.out.println("File not found.");
        return;
      }
    } else {
      inputScanner = new InputTerminal();
    }

    Board board = new Board(5, 5);

    handleInput(inputScanner, board);
    inputScanner.close();
  }
}


