public class OutputTerminal implements OutputInterface {

  @Override
  public void print(String output) {
    System.out.print(output);
  }

  @Override
  public void println(String output) {
    System.out.println(output);
  }
}
