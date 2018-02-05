package taskHandler;
import java.io.PrintWriter;
import static java.lang.System.out;
import static java.lang.Thread.sleep;

public class RestyleHaircutTask implements Task {
    private PrintWriter outStream;
    private String taskName;

    public RestyleHaircutTask(String taskName, PrintWriter outStream){
        this.taskName = taskName;
        this.outStream = outStream;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void run() {
        try {
            sleep((int)(Math.random()*1000));
        } catch (InterruptedException e){
            out.println(String.format("Failed executing task: %s", getTaskName()));
        }
        outStream.println("Customer haircut has been restyled. Please pay your service.");
    }
}
