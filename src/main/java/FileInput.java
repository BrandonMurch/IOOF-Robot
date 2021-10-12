import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInput extends UserInput {

  public FileInput(String fileName) throws FileNotFoundException {
    super(new Scanner(new File(fileName)));
  }

  @Override
  public boolean hasNext() {
    return super.getUnderlyingScanner().hasNext();
  }
}
