package common;
import java.io.PrintWriter;

public class Customer {
  private Entrance entrance;
  private String type;
  private  PrintWriter outStream;

  public Customer(Entrance entrance, String type, PrintWriter outStream) {
    this.entrance = entrance;
    this.type = type;
    this.outStream = outStream;
  }

  public void takeSeat() {
    this.entrance.takeSeat(type, outStream);
  }

  public void freeSeat() {
    this.entrance.freeSeat(type, outStream);
  }
}
