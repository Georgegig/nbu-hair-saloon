package ui;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class StartProcedureL implements ActionListener {
  private PrintWriter outStream;
  private BufferedReader inStream;
  private  TextArea outputArea;
  private  TextField hairStyleTextField;

  StartProcedureL(PrintWriter outStream, BufferedReader inStream, TextArea outputArea, TextField hairStyleTextField ) {
    this.outStream = outStream;
    this.inStream = inStream;
    this.outputArea = outputArea;
    this.hairStyleTextField = hairStyleTextField;
  }

  public void actionPerformed(ActionEvent e) {
    outStream.println(String.format("procedure:%s", hairStyleTextField.getText()));
    outputArea.setText("Working on the haircut");
    String serverResponse;
    try {
      while (true) {
        if (inStream.ready()) {
          serverResponse = inStream.readLine();
          break;
        }
      }
      outputArea.setText(serverResponse);
    } catch (IOException e1) {
      outputArea.setText("Something went wrong when trying get haircut in the saloon");
      e1.printStackTrace();
    }
  }
}
