package hairSaloon;
import common.Customer;
import common.Entrance;
import taskHandler.*;
import static java.lang.System.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HairSaloonServer {

  public static void main(String[] args) throws Exception {
    out.println("The hair saloon server is running.");
    ServerSocket serverSocket = new ServerSocket(5400);
    TaskHandler taskHandler = new TaskHandler(10);
    Entrance entrance = new Entrance(2);
    try {
      while (true) {
        new Order(serverSocket.accept(), taskHandler, entrance).start();
      }
    } catch (Exception exc) {
      out.println("Error! - " + exc.toString());
    }
  }

  private static class Order extends Thread {
    private Socket socket;
    private TaskHandler taskHandler;
    private Entrance entrance;

    Order(Socket socket, TaskHandler taskHandler, Entrance entrance) {
      this.socket = socket;
      this.taskHandler = taskHandler;
      this.entrance = entrance;
    }

    // run method implemented by Thread class
    @Override
    public void run() {
      try {
        BufferedReader dataInputStream = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        PrintWriter printStream = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())),true);

        while (true) {
          out.println("Debug: Wait for client request");
          String request = dataInputStream.readLine();
          out.println("Debug: " + request);

          Customer customer = new Customer(this.entrance, "RealCustomer", printStream);

          if ("enter".equals(request)) {
            EnterHairSaloonTask enterTask = new EnterHairSaloonTask("RealCustomerEnterTask", customer);
            taskHandler.executeTask(enterTask);
          }
          else if (request.contains("procedure:")){
            StartProcedureTask startProcedureTask = new StartProcedureTask("RealCustomerProcedureTask",
                    request, printStream);
            taskHandler.executeTask(startProcedureTask);
          }
          else if ("restyle".equals(request)){
            RestyleHaircutTask restyleHaircutTask = new RestyleHaircutTask("RealCustomerRestyleTask", printStream);
            taskHandler.executeTask(restyleHaircutTask);
          }
          else if ("pay".equals(request)){
            PayHaircutTask payHaircutTask = new PayHaircutTask("RealCustomerPayTask", printStream);
            taskHandler.executeTask(payHaircutTask);
          }
          else if ("leave".equals(request)){
            LeaveHairSaloonTask leaveTask = new LeaveHairSaloonTask("RealCustomerLeaveTask", socket, customer);
            taskHandler.executeTask(leaveTask);
          }
        }
      } catch (NullPointerException exc) {
        out.println("Client has closed the connection!");
      } catch (Exception exc) {
        out.println("Error! - " + exc.toString());
      }

      try {
        socket.close();
      } catch (Exception exc) {
        out.println("Error! - " + exc.toString());
      }
    }
  }
}