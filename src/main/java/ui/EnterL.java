package ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class EnterL implements ActionListener {
  private PrintWriter outStream;
  private BufferedReader inStream;
  private TextArea outputArea;

  EnterL(PrintWriter outStream, BufferedReader inStream, TextArea outputArea) {
    this.outStream = outStream;
    this.inStream = inStream;
    this.outputArea = outputArea;
  }

  public void actionPerformed(ActionEvent e) {
    outputArea.setText("Client is waiting to enter the saloon");
    outStream.println("enter");
    try {
      String serverResponse = inStream.readLine();
      outputArea.setText(serverResponse);
    } catch (IOException e1) {
      outputArea.setText("Something went wrong when trying to enter the hair saloon");
      e1.printStackTrace();
    }
  }
}
