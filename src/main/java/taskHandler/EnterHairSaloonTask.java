package taskHandler;
import common.Customer;
import static java.lang.System.out;

public class EnterHairSaloonTask implements Task {
  private String taskName;
  private Customer customer;

  public EnterHairSaloonTask(String taskName, Customer customer) {
    this.taskName = taskName;
    this.customer = customer;
  }

  public void run() {
    try {
      this.customer.takeSeat();
    } catch (Exception exc) {
      out.println("Error in entering the hair saloon - " + exc.toString());
    }
  }

  public String getTaskName() {
    return this.taskName;
  }
}
