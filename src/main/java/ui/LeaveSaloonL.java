package ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LeaveSaloonL implements ActionListener {
  private PrintWriter outStream;
  private BufferedReader inStream;
  private TextArea outputArea;

  LeaveSaloonL(PrintWriter outStream, BufferedReader inStream, TextArea outputArea) {
    this.outStream = outStream;
    this.inStream = inStream;
    this.outputArea = outputArea;
  }

  public void actionPerformed(ActionEvent e) {
    outputArea.setText("Leaving saloon.");
    outStream.println("leave");
    try {
      String serverResponse = inStream.readLine();
      outputArea.setText(serverResponse);
      System.exit(0);
    } catch (IOException e1) {
      outputArea.setText("Something went wrong when trying to leave the hair saloon");
      e1.printStackTrace();
    }
  }
}
