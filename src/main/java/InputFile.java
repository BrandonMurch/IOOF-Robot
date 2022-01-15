import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputFile extends InputAbstract {

  public InputFile(String fileName) throws FileNotFoundException {
    super(new Scanner(new File(fileName)));
  }

  @Override
  public boolean hasNext() {
    return super.hasNext();
  }
}
