package taskHandler;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;
import static java.lang.Thread.sleep;

public class StartProcedureTask implements Task {
  private String taskName;
  private String userData;
  private PrintWriter outStream;

  public StartProcedureTask(String taskName, String userData, PrintWriter outStream) {
    this.taskName = taskName;
    this.userData = userData;
    this.outStream = outStream;
  }

  public void run() {
      List<String> userDataList = Arrays.asList(userData.split(":"));
      String userOder = "";
      try {
          userOder += userDataList.get(1);
      } catch (IndexOutOfBoundsException e) {
          outStream.println("Please describe your desired haircut");
          return;
      }
      try {
          sleep((int)(Math.random()*1000));
      } catch (InterruptedException e){
          out.println(String.format("Failed executing task: %s", getTaskName()));
      }

      outStream.println("Client haircut style is: " + userOder + " If you like your hairstyle you can proceed to payment. Else you can restyle your hair.");
  }

  public String getTaskName() {
    return this.taskName;
  }
}
