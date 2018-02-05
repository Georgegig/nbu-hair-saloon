package taskHandler;
import java.io.PrintWriter;

public class PayHaircutTask implements Task {
    private PrintWriter outStream;
    private String taskName;

    public PayHaircutTask(String taskName, PrintWriter outStream) {
        this.taskName = taskName;
        this.outStream = outStream;
    }

    public void run() {
        outStream.println("Customer has paid for the haircut");
    }

    public String getTaskName() {
        return taskName;
    }
}
