package common;
import static java.lang.System.out;
import java.io.PrintWriter;

public class Entrance {
  private int seatCount;

  public Entrance(int seatCount){
    this.seatCount = seatCount;
  }

  public synchronized void takeSeat(String type, PrintWriter outStream) {
    while (seatCount == 0) {
      printMessage("log", outStream, " waiting for a free seat");
      try {
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        out.println(e.getMessage());
      }
    }
    seatCount--;
    out.println(String.format("Seats in the saloon after taking one: %s", seatCount));
    printMessage(type, outStream, " entered the saloon");
  }

  public synchronized void freeSeat(String type, PrintWriter outStream) {
    seatCount++;
    out.println(String.format("Seats in the saloon after freeing one: %s", seatCount));
    printMessage(type, outStream, " left the saloon");
    notifyAll();
  }

  private void printMessage(String type, PrintWriter outStream, String message) {
    if ("RealCustomer".equals(type)) {
      outStream.println(type + message);
      out.println(type + message);
    } else {
      out.println(Thread.currentThread().getName() + message);
    }
  }
}
