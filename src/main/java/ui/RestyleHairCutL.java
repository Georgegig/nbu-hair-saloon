package ui;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RestyleHairCutL implements ActionListener {
  private PrintWriter outStream;
  private BufferedReader inStream;
  private TextArea outputArea;


  RestyleHairCutL(PrintWriter outStream, BufferedReader inStream, TextArea outputArea) {
    this.outStream = outStream;
    this.inStream = inStream;
    this.outputArea = outputArea;
  }

  public void actionPerformed(ActionEvent e) {
    outputArea.setText("Restyling haircut");
    outStream.println("restyle");
    try {
      String serverResponse;
      while (true) {
        if (inStream.ready()) {
          serverResponse = inStream.readLine();
          break;
        }
      }
      outputArea.setText(serverResponse);
    } catch (IOException e1) {
      outputArea.setText("Something went wrong when trying to restyle the haircut");
      e1.printStackTrace();
    }
  }
}
