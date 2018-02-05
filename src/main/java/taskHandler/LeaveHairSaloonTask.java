package taskHandler;
import static java.lang.System.out;
import common.Customer;
import java.io.PrintWriter;
import java.net.Socket;

public class LeaveHairSaloonTask implements Task {
  private String taskName;
  private Socket socket;
  private Customer customer;

  public LeaveHairSaloonTask(String taskName, Socket socket, Customer customer) {
    this.taskName = taskName;
    this.socket = socket;
    this.customer = customer;
  }

  public void run() {
    this.customer.freeSeat();
    try {
      socket.close();
    } catch (Exception exc) {
      out.println("Error in close the socket - " + exc.toString());
    }
  }

  public String getTaskName() {
    return taskName;
  }
}
